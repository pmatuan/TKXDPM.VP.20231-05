package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.*;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.repository.order.DeliveryInfoRepository;
import vn.hust.aims.repository.order.OrderRepository;
import vn.hust.aims.repository.order.RushOrderRepository;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;
import vn.hust.aims.service.dto.input.placeorder.GetOrderInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.GetOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final DeliveryInfoRepository deliveryInfoRepository;
  private final RushOrderRepository rushOrderRepository;

  public CreateOrderOutput createOrderFromCart(CreateOrderInput input) {

    Cart cart = getCartById(input.getCartId());

    String orderId = UUID.randomUUID().toString();
    List<OrderMedia> orderMediaList = mapCartMediaToOrderMedia(cart.getCartMediaList());

    Double subtotal = calculateSubtotal(orderMediaList);
    Double VAT = calculateVAT(subtotal);
    Double deliveryFee = calculateDeliveryFee(orderMediaList);
    Double total = calculateTotal(subtotal, VAT, deliveryFee);

    Order order = buildOrder(orderId, orderMediaList, subtotal, VAT, deliveryFee, total);
    orderRepository.save(order);

    return CreateOrderOutput.from(orderId);
  }

  public GetOrderOutput getOrder(GetOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    return GetOrderOutput.from(order);
  }

  public UpdateDeliveryInfoOutput updateDeliveryInfo(UpdateDeliveryInfoInput input) {

    Order order = getOrderById(input.getOrderId());

    DeliveryInfo deliveryInfo = createDeliveryInfo(input);
    saveDeliveryInfo(deliveryInfo);

    order.setDeliveryInfo(deliveryInfo);
    saveOrder(order);

    if (Boolean.TRUE.equals(input.getIsOrderForRushDelivery())) {
      RushOrder rushOrder = createRushOrder(order, input);
      saveRushOrder(rushOrder);
    }
    return UpdateDeliveryInfoOutput.from(
        "Update delivery info to order " + input.getOrderId() + " successfully");
  }

  private List<OrderMedia> mapCartMediaToOrderMedia(List<CartMedia> cartMediaList) {
    return cartMediaList.stream()
        .map(OrderMedia::from)
        .collect(Collectors.toList());
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

  private Order buildOrder(String orderId, List<OrderMedia> orderMediaList, Double subtotal,
      Double VAT, Double deliveryFee, Double total) {

    return Order.builder()
        .id(orderId)
        .orderMediaList(orderMediaList)
        .subtotal(subtotal)
        .vat(VAT)
        .deliveryFee(deliveryFee)
        .total(total)
        .build();
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

  private DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input) {

    return DeliveryInfo.builder()
        .customerName(input.getCustomerName())
        .email(input.getEmail())
        .phoneNumber(input.getPhoneNumber())
        .city(input.getProvince().toString())
        .address(input.getAddress())
        .build();
  }

  private void saveDeliveryInfo(DeliveryInfo deliveryInfo) {
    deliveryInfoRepository.save(deliveryInfo);
  }

  private RushOrder createRushOrder(Order order, UpdateDeliveryInfoInput input) {
    return RushOrder.builder()
        .order(order)
        .deliveryTime(input.getDeliveryTime())
        .deliveryInstruction(input.getDeliveryInstruction())
        .build();
  }

  private void saveRushOrder(RushOrder rushOrder) {
    rushOrderRepository.save(rushOrder);
  }

  private void saveOrder(Order order) {
    orderRepository.save(order);
  }
}
