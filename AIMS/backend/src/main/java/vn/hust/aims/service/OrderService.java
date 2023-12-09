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
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.*;
import vn.hust.aims.exception.OrderMediaNotFoundException;
import vn.hust.aims.exception.OrderNotFoundException;
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
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMediaRepository orderMediaRepository;
  private final RushOrderRepository rushOrderRepository;
  private final CalculationService calculationService;
  private final DeliveryInfoService deliveryInfoService;
  private final MediaService mediaService;
  private final CartService cartService;

  public CreateOrderOutput createOrderFromCart(CreateOrderInput input) {
    Cart cart = cartService.getCartById(input.getCartId());

    List<OrderMedia> orderMediaList = mapCartMediaToOrderMedia(cart.getCartMediaList());

    // data coupling
    Order order = createOrder(orderMediaList);

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
    DeliveryInfo deliveryInfo = deliveryInfoService.createDeliveryInfo(input);
    order.setDeliveryInfo(deliveryInfo);

    if (Boolean.TRUE.equals(input.getIsOrderForRushDelivery())) {
      // data coupling
      updateOrderForRushDelivery(order, input);
    }

    // data coupling
    updateOrder(order);

    return UpdateDeliveryInfoOutput.from(
        "Update delivery info to order " + input.getOrderId() + " successfully");
  }

  public UpdateMediaInOrderOutput updateOrderMedia(UpdateMediaInOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    // stamp coupling: only the media list of order is used: fix
    OrderMedia orderMedia = findOrderMediaById(order.getOrderMediaList(), input.getOrderMediaId());

    // data coupling
    mediaService.validateQuantityInStock(orderMedia.getMedia(), input.getQuantity());
    // data coupling
    updateOrderMediaQuantity(orderMedia, input.getQuantity());
    // data coupling
    updateOrder(order);

    return UpdateMediaInOrderOutput.from(
        "Update quantity order media " + input.getOrderMediaId() + " to " + input.getQuantity()
            + " successfully");
  }

  public DeleteMediaInOrderOutput deleteOrderMedia(DeleteMediaInOrderInput input) {

    Order order = getOrderById(input.getOrderId());

    // stamp coupling: only the media list of order is used: fix
    OrderMedia orderMedia = findOrderMediaById(order.getOrderMediaList(), input.getOrderMediaId());

    // data coupling
    deleteOrderMedia(orderMedia, order);

    return DeleteMediaInOrderOutput.from(
        "Deleted order media " + input.getOrderMediaId() + " successfully");
  }

  private Order getOrderById(String orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException());
  }

  private List<OrderMedia> mapCartMediaToOrderMedia(List<CartMedia> cartMediaList) {
    return cartMediaList.stream()
        .map(OrderMedia::from)
        .collect(Collectors.toList());
  }

  private Order createOrder(List<OrderMedia> orderMediaList) {

    String orderId = UUID.randomUUID().toString();

    // data coupling
    Double subtotal = calculationService.calculateOrderMediaSubtotal(orderMediaList);
    // data coupling
    Double VAT = calculationService.calculateVAT(subtotal);
    // data coupling
    Double total = calculationService.calculateTotal(subtotal, VAT);

    Order order = Order.builder()
        .id(orderId)
        .orderMediaList(orderMediaList)
        .subtotal(subtotal)
        .vat(VAT)
        .total(total)
        .build();
    orderRepository.save(order);

    orderMediaList.forEach(orderMedia -> orderMedia.setOrder(order));
    orderMediaRepository.saveAll(orderMediaList);

    return order;
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
        .orElseThrow(() -> new OrderMediaNotFoundException());
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

  private void deleteOrderMedia(OrderMedia orderMedia, Order order) {
    orderMediaRepository.delete(orderMedia);
    order.removeOrderMedia(orderMedia);
    // data coupling
    updateOrder(order);
  }

  private void updateOrder(Order order) {

    // data coupling
    Double subtotal = calculationService.calculateOrderMediaSubtotal(order.getOrderMediaList());
    order.setSubtotal(subtotal);

    // data coupling
    Double VAT = calculationService.calculateVAT(subtotal);
    order.setVat(VAT);

    // data coupling
    Double deliveryFee = calculationService.calculateDeliveryFee(order);
    order.setDeliveryFee(deliveryFee);

    // data coupling
    Double total = calculationService.calculateTotal(order.getSubtotal(), order.getVat(), deliveryFee);
    order.setTotal(total);

    orderRepository.save(order);
  }

  private void updateOrderMediaQuantity(OrderMedia orderMedia, Integer quantity) {
    orderMedia.setQuantity(quantity);
    orderMediaRepository.save(orderMedia);
  }

}
