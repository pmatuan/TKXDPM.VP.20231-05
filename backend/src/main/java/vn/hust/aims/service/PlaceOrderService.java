package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.entity.order.*;
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

    Order order = buildOrder(orderMediaList);
    orderRepository.save(order);

    orderMediaList.forEach(orderMedia -> orderMedia.setOrder(order));
    orderMediaRepository.saveAll(orderMediaList);

    return CreateOrderOutput.from(order.getId());
  }


  public GetOrderOutput getOrder(GetOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    return GetOrderOutput.from(order);
  }

  public UpdateDeliveryInfoOutput updateDeliveryInfo(UpdateDeliveryInfoInput input) {

    Order order = getOrderById(input.getOrderId());

    DeliveryInfo deliveryInfo = createDeliveryInfo(input);
    deliveryInfoRepository.save(deliveryInfo);

    order.setDeliveryInfo(deliveryInfo);
    orderRepository.save(order);

    if (Boolean.TRUE.equals(input.getIsOrderForRushDelivery())) {
      order.getOrderMediaList().stream()
          .forEach(orderMedia -> {
            if (orderMedia.getMedia().getIsAbleToRushDelivery()){
              orderMedia.setIsOrderForRushDelivery(true);
            }
          });

      RushOrder rushOrder = createRushOrder(order, input);
      rushOrderRepository.save(rushOrder);
    }
    return UpdateDeliveryInfoOutput.from(
        "Update delivery info to order " + input.getOrderId() + " successfully");
  }

  public UpdateMediaInOrderOutput updateOrderMedia(UpdateMediaInOrderInput input) {
    Order order = getOrderById(input.getOrderId());
    OrderMedia orderMedia = findOrderMediaById(order, input.getOrderMediaId());

    validateAndUpdateOrderMedia(orderMedia, input.getQuantity(), order);

    return UpdateMediaInOrderOutput.from(
        "Update quantity order media " + input.getOrderMediaId() + " to " + input.getQuantity() + " successfully");
  }

  public DeleteMediaInOrderOutput deleteOrderMedia(DeleteMediaInOrderInput input) {
    Order order = getOrderById(input.getOrderId());
    OrderMedia orderMedia = findOrderMediaById(order, input.getOrderMediaId());

    deleteAndUpdateOrderMedia(orderMedia, order);

    return DeleteMediaInOrderOutput.from("Deleted order media " + input.getOrderMediaId() + " successfully");
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

    Double subtotal = calculateSubtotal(orderMediaList);
    Double VAT = calculateVAT(subtotal);
    Double deliveryFee = calculateDeliveryFee(orderMediaList);
    Double total = calculateTotal(subtotal, VAT, deliveryFee);

    return Order.builder()
        .id(orderId)
        .orderMediaList(orderMediaList)
        .subtotal(subtotal)
        .vat(VAT)
        .deliveryFee(deliveryFee)
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

  private Double calculateDeliveryFee(List<OrderMedia> orderMediaList) {
    // TODO: Implement delivery fee calculation
    return 0.0;
  }

  private Double calculateTotal(Double subtotal, Double VAT, Double deliveryFee) {
    return subtotal + VAT + deliveryFee;
  }

  private DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input) {
    return DeliveryInfo.builder()
        .customerName(input.getCustomerName())
        .email(input.getEmail())
        .phoneNumber(input.getPhoneNumber())
        .city(input.getProvince().toString())
        .address(input.getAddress())
        .build();
  }

  private RushOrder createRushOrder(Order order, UpdateDeliveryInfoInput input) {
    return RushOrder.builder()
        .order(order)
        .deliveryTime(input.getDeliveryTime())
        .deliveryInstruction(input.getDeliveryInstruction())
        .build();
  }

  private OrderMedia findOrderMediaById(Order order, Long orderMediaId) {
    return order.getOrderMediaList().stream()
        .filter(orderMedia -> orderMedia.getId().equals(orderMediaId))
        .findFirst()
        .orElseThrow(() -> new AimsException(null, ErrorCodeList.ORDER_MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  private void validateAndUpdateOrderMedia(OrderMedia orderMedia, Integer quantity, Order order) {
    validateQuantityInStock(orderMedia.getMedia(), quantity);
    updateOrderMediaQuantity(orderMedia, quantity);
    updateOrder(order);
  }

  private void deleteAndUpdateOrderMedia(OrderMedia orderMedia, Order order) {
    orderMediaRepository.delete(orderMedia);
    order.getOrderMediaList().remove(orderMedia);
    updateOrder(order);
  }

  private void validateQuantityInStock(Media media, Integer requestedQuantity) {
    if (media.getQuantityInStock() < requestedQuantity) {
      throw new AimsException(null, ErrorCodeList.QUANTITY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
    }
  }

  private void updateOrder(Order order) {

    List<OrderMedia> orderMediaList = order.getOrderMediaList();

    Double subtotal = calculateSubtotal(orderMediaList);
    Double VAT = calculateVAT(subtotal);
    Double deliveryFee = calculateDeliveryFee(orderMediaList);
    Double total = calculateTotal(subtotal, VAT, deliveryFee);

    order.setSubtotal(subtotal);
    order.setVat(VAT);
    order.setDeliveryFee(deliveryFee);
    order.setTotal(total);

    orderRepository.save(order);
  }

  private void updateOrderMediaQuantity(OrderMedia orderMedia, Integer quantity) {
    orderMedia.setQuantity(quantity);
    orderMediaRepository.save(orderMedia);
  }

}
