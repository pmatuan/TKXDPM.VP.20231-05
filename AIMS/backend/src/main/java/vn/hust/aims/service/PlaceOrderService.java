/*
  Functions in this class are all loosely coupled with other functions. However, the large amount of imported functions from other classes,
  along with this class' attempt to cover all aspects of order placing process, make it become highly coupled to other classes,
  while having low cohesion between functions.
  Proposed solution:
   - Separation of concerns: have a separate class for order creation, another for delivery information handling, and another for
   order calculation.
*/
package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.entity.order.*;
import vn.hust.aims.enumeration.ProvinceEnum;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.repository.order.DeliveryInfoRepository;
import vn.hust.aims.repository.order.OrderMediaRepository;
import vn.hust.aims.repository.order.OrderRepository;
import vn.hust.aims.repository.order.RushOrderRepository;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;
import vn.hust.aims.service.dto.input.placeorder.DeleteMediaInOrderInput;
import vn.hust.aims.service.dto.input.placeorder.GetOrderInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateMediaInOrderInput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.DeleteMediaInOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.GetOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import vn.hust.aims.service.dto.output.placeorder.UpdateMediaInOrderOutput;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final OrderMediaRepository orderMediaRepository;
  private final DeliveryInfoRepository deliveryInfoRepository;
  private final RushOrderRepository rushOrderRepository;

  public CreateOrderOutput createOrderFromCart(CreateOrderInput input) {
    Cart cart = getCartById(input.getCartId());

    List<OrderMedia> orderMediaList = mapCartMediaToOrderMedia(cart.getCartMediaList());

    // data coupling
    Order order = buildOrder(orderMediaList);
    orderRepository.save(order);

    orderMediaList.forEach(orderMedia -> orderMedia.setOrder(order));
    orderMediaRepository.saveAll(orderMediaList);

    // data coupling
    return CreateOrderOutput.from(order.getId());
  }


  public GetOrderOutput getOrder(GetOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    return GetOrderOutput.from(order);
  }

  public UpdateDeliveryInfoOutput updateDeliveryInfo(UpdateDeliveryInfoInput input) {

    Order order = getOrderById(input.getOrderId());

    // data coupling
    DeliveryInfo deliveryInfo = createDeliveryInfo(input);
    deliveryInfoRepository.save(deliveryInfo);
    order.setDeliveryInfo(deliveryInfo);

    if (Boolean.TRUE.equals(input.getIsOrderForRushDelivery())) {
      // data coupling
      updateOrderForRushDelivery(order, input);
    }

    // data coupling
    updateShippingFeeOrder(order);

    return UpdateDeliveryInfoOutput.from(
        "Update delivery info to order " + input.getOrderId() + " successfully");
  }

  public UpdateMediaInOrderOutput updateOrderMedia(UpdateMediaInOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    // stamp coupling: only the media list of order is used: fix
    OrderMedia orderMedia = findOrderMediaById(order.getOrderMediaList(), input.getOrderMediaId());

    // data coupling
    validateQuantityInStock(orderMedia.getMedia(), input.getQuantity());
    // data coupling
    updateOrderMediaQuantity(orderMedia, input.getQuantity());
    // data coupling
    updateShippingFeeOrder(order);

    return UpdateMediaInOrderOutput.from(
        "Update quantity order media " + input.getOrderMediaId() + " to " + input.getQuantity()
            + " successfully");
  }

  public DeleteMediaInOrderOutput deleteOrderMedia(DeleteMediaInOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    // stamp coupling: only the media list of order is used: fix
    OrderMedia orderMedia = findOrderMediaById(order.getOrderMediaList(), input.getOrderMediaId());

    // data coupling
    deleteAndUpdateOrderMedia(orderMedia, order);

    return DeleteMediaInOrderOutput.from(
        "Deleted order media " + input.getOrderMediaId() + " successfully");
  }

  private Cart getCartById(String cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  private Order getOrderById(String orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.ORDER_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  private List<OrderMedia> mapCartMediaToOrderMedia(List<CartMedia> cartMediaList) {
    return cartMediaList.stream()
        .map(OrderMedia::from)
        .collect(Collectors.toList());
  }

  private Order buildOrder(List<OrderMedia> orderMediaList) {

    String orderId = UUID.randomUUID().toString();

    // data coupling
    Double subtotal = calculateSubtotal(orderMediaList);
    // data coupling
    Double VAT = calculateVAT(subtotal);
    // data coupling
    Double total = calculateTotal(subtotal, VAT);

    return Order.builder()
        .id(orderId)
        .orderMediaList(orderMediaList)
        .subtotal(subtotal)
        .vat(VAT)
        .total(total)
        .build();
  }

  private Double calculateSubtotal(List<OrderMedia> orderMediaList) {
    return orderMediaList.stream()
        .mapToDouble(orderMedia -> orderMedia.getMedia().getPrice() * orderMedia.getQuantity())
        .sum();
  }

  private Double calculateVAT(Double subtotal) {
    return subtotal / 10; // VAT is 10% of the subtotal
  }

  private Double calculateDeliveryFee(Order order) {

    if (order.getDeliveryInfo() == null) {
      return null;
    }

    List<OrderMedia> rushDeliveryItems = order.getOrderMediaList().stream()
        .filter(orderMedia -> orderMedia.getIsOrderForRushDelivery())
        .collect(Collectors.toList());

    Long totalRushDeliveryQuantity = rushDeliveryItems.stream()
        .mapToLong(OrderMedia::getQuantity)
        .sum();

    Double rushDeliveryFee = (double) (totalRushDeliveryQuantity * 10000);

    if (order.getSubtotal() > 100000) {
      return 0.0 + rushDeliveryFee;
    }

    Double maxWeight = order.getOrderMediaList().stream()
        .map(orderMedia -> orderMedia.getMedia().getWeight())
        .max(Double::compare)
        .orElse(0.0);

    String city = order.getDeliveryInfo().getCity();

    if (isInHanoi(city)) {
      // data coupling
      return calculateFeeInHanoiAndHCM(maxWeight) + rushDeliveryFee;
    } else if (isInHCM(city)) {
      // data coupling
      return calculateFeeInHanoiAndHCM(maxWeight);
    } else {
      // data coupling
      return calculateFeeOutside(maxWeight);
    }
  }

  private Boolean isInHanoi(String city) {
    return city.equals(ProvinceEnum.HANOI.getStringValue());
  }

  private Boolean isInHCM(String city) {
    return city.equals(ProvinceEnum.HOCHIMINH.getStringValue());
  }

  private Double calculateFeeInHanoiAndHCM(Double maxWeight) {
    if (maxWeight <= 3.0) {
      return 22000.0;
    } else {
      Double additionalWeight = maxWeight - 3.0;
      Double additionalFee = Math.ceil(additionalWeight / 0.5) * 2500;
      return 22000.0 + additionalFee;
    }
  }

  private Double calculateFeeOutside(Double maxWeight) {
    Double additionalWeight = maxWeight - 0.5; // Subtract the initial 0.5kg
    Double additionalFee = Math.ceil(additionalWeight / 0.5) * 2500;
    return 30000 + additionalFee;
  }

  private Double calculateTotal(Double subtotal, Double VAT) {
    return subtotal + VAT;
  }

  private Double calculateTotal(Double subtotal, Double VAT, Double deliveryFee) {
    return subtotal + VAT + deliveryFee;
  }

  private DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input) {
    return DeliveryInfo.builder()
        .customerName(input.getCustomerName())
        .email(input.getEmail())
        .phoneNumber(input.getPhoneNumber())
        .city(input.getProvince().getStringValue())
        .address(input.getAddress())
        .build();
  }

  private RushOrder createRushOrder(Order order, UpdateDeliveryInfoInput input) {
    return RushOrder.builder()
        .id(order.getId())
        .order(order)
        .deliveryTime(input.getDeliveryTime())
        .deliveryInstruction(input.getDeliveryInstruction())
        .build();
  }

  private OrderMedia findOrderMediaById(List<OrderMedia> orderMediaList, Long orderMediaId) {
    return orderMediaList.stream()
        .filter(orderMedia -> orderMedia.getId().equals(orderMediaId))
        .findFirst()
        .orElseThrow(() -> new AimsException(null, ErrorCodeList.ORDER_MEDIA_NOT_FOUND,
            HttpStatus.BAD_REQUEST));
  }

  private void updateOrderForRushDelivery(Order order, UpdateDeliveryInfoInput input) {
    order.getOrderMediaList().forEach(orderMedia -> {
      if (orderMedia.getMedia().getIsAbleToRushDelivery()) {
        orderMedia.setIsOrderForRushDelivery(true);
      }
    });

    // data coupling
    RushOrder rushOrder = createRushOrder(order, input);
    rushOrderRepository.save(rushOrder);
  }

  private void deleteAndUpdateOrderMedia(OrderMedia orderMedia, Order order) {
    orderMediaRepository.delete(orderMedia);
    order.removeOrderMedia(orderMedia);
    // data coupling
    updateShippingFeeOrder(order);
  }

  private void validateQuantityInStock(Media media, Integer requestedQuantity) {
    if (media.getQuantityInStock() < requestedQuantity) {
      throw new AimsException(null, ErrorCodeList.QUANTITY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
    }
  }

  private void updateShippingFeeOrder(Order order) {

    // data coupling
    Double deliveryFee = calculateDeliveryFee(order);
    order.setDeliveryFee(deliveryFee);

    // data coupling
    Double total = calculateTotal(order.getSubtotal(), order.getVat(), deliveryFee);
    order.setTotal(total);

    orderRepository.save(order);
  }

  private void updateOrderMediaQuantity(OrderMedia orderMedia, Integer quantity) {
    orderMedia.setQuantity(quantity);
    orderMediaRepository.save(orderMedia);
  }

}
