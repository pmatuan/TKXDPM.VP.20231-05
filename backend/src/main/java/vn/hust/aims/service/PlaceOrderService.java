package vn.hust.aims.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.repository.order.OrderRepository;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;

  public CreateOrderOutput createOrderFromCart(CreateOrderInput input) {
    Cart cart = cartRepository.findById(input.getCartId())
        .orElseThrow(() -> new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST));

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
    // VAT is 10% of the subtotal
    return subtotal / 10;
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
}
