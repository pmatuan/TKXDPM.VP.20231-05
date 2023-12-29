/*
  Functional cohesion: hầu hết các thành phần của lớp này đều hướng đến việc thực hiện một trách nhiệm chung rõ ràng là tính toán hóa đơn. Mỗi phương thức sử dụng kết quả của phương thức khác theo một quy trình.
  Các thành phần updateOrderForRushDelivery, createRushOrder, rushOrderRepository có thể được tách ra 1 lớp riêng để tăng cohesion.
*/

package vn.hust.aims.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.email.Param;
import vn.hust.aims.entity.order.*;
import vn.hust.aims.enumeration.OrderStateEnum;
import vn.hust.aims.exception.CannotCancelOrderException;
import vn.hust.aims.exception.CannotChangeOrderStateException;
import vn.hust.aims.exception.NotSupportRushDeliveryException;
import vn.hust.aims.exception.OrderMediaNotFoundException;
import vn.hust.aims.exception.OrderNotFoundException;
import vn.hust.aims.repository.email.SenderRepository;
import vn.hust.aims.repository.email.TemplateRepository;
import vn.hust.aims.repository.order.OrderMediaRepository;
import vn.hust.aims.repository.order.OrderRepository;
import vn.hust.aims.repository.order.RushOrderRepository;
import vn.hust.aims.service.dto.input.cancelorder.CancelOrderInput;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.order.UpdateOrderStateInput;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;
import vn.hust.aims.service.dto.input.placeorder.DeleteMediaInOrderInput;
import vn.hust.aims.service.dto.input.order.GetOrderInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateMediaInOrderInput;
import vn.hust.aims.service.dto.output.cancelorder.CancelOrderOutput;
import vn.hust.aims.service.dto.output.order.GetAllOrderOutput;
import vn.hust.aims.service.dto.output.order.UpdateOrderStateOutput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.DeleteMediaInOrderOutput;
import vn.hust.aims.service.dto.output.order.GetOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import vn.hust.aims.service.dto.output.placeorder.UpdateMediaInOrderOutput;
import vn.hust.aims.service.media.MediaService;
import vn.hust.aims.utils.TextEngineUtil;

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
  private final MailService mailService;
  @Value(value = "${url.frontend}")
  private String frontendUrl;

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
    RushOrder rushOrder = getRushOrderById(input.getOrderId());

    return GetOrderOutput.from(order, rushOrder);
  }

  public GetAllOrderOutput getAllOrder(Pageable pageable) {
    Page<Order> orderPage = orderRepository.getAllOrderPage(pageable);
    return GetAllOrderOutput.from(orderPage);
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
    updateOrderMediaQuantity(orderMedia, order, input.getQuantity());
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
    deleteOrderMediaFromRepository(orderMedia, order);
    // data coupling
    updateOrder(order);

    return DeleteMediaInOrderOutput.from(
        "Deleted order media " + input.getOrderMediaId() + " successfully");
  }

  public UpdateOrderStateOutput updateOrderState(UpdateOrderStateInput input) {
    Order order = getOrderById(input.getOrderId());

    checkOrderStateTransition(order, input.getState());

    updateOrderState(order, input.getState());

    sendOrderConfirmationEmail(order, input.getState());

    return UpdateOrderStateOutput.from(
        "Updated order " + input.getOrderId() + " to state " + input.getState().getStringValue() + " successfully"
    );
  }

  private void checkOrderStateTransition(Order order, OrderStateEnum newState) {
    OrderStateEnum currentState = OrderStateEnum.from(order.getState());

    if (currentState.getIntValue() >= newState.getIntValue()) {
      throw new CannotChangeOrderStateException();
    }
  }

  private void updateOrderState(Order order, OrderStateEnum newState) {
    order.setState(newState.getStringValue());
    orderRepository.save(order);
  }

  private void sendOrderConfirmationEmail(Order order, OrderStateEnum newState) {
    String templateName = (newState == OrderStateEnum.ACCEPT) ? "Đơn hàng đã được duyệt" : "Đơn hàng đã bị từ chối";

    List<Param> params = Arrays.asList(
        Param.builder().key("orderId").value(order.getId()).build(),
        Param.builder().key("trace_order_link").value(frontendUrl + "/trace-order?orderId=" + order.getId()).build()
    );

    mailService.send(
        SendEmailInput.builder()
            .status(true)
            .destination(getCustomerEmailFromOrder(order.getId()))
            .templateName(templateName)
            .params(params)
            .build()
    );
  }

  public CancelOrderOutput cancelOrder(CancelOrderInput input) {
    Order order = getOrderById(input.getOrderId());

    OrderStateEnum currentState = OrderStateEnum.from(order.getState());
    OrderStateEnum cancelState = OrderStateEnum.CANCEL;

    if (currentState.getIntValue() >= cancelState.getIntValue()) {
      throw new CannotCancelOrderException();
    }

    order.setState(cancelState.getStringValue());

    // TODO: redirect to refund

    return CancelOrderOutput.from("Cancelled order " + input.getOrderId() + " successfully");
  }

  public Order getOrderById(String orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException());
  }

  public void saveOrder(Order order) {
    orderRepository.save(order);
  }

  public String getCustomerEmailFromOrder(String orderId) {
    Order order = getOrderById(orderId);
    DeliveryInfo deliveryInfo = order.getDeliveryInfo();
    return deliveryInfo.getEmail();
  }

  private RushOrder getRushOrderById(String orderId) {
    return rushOrderRepository.findById(orderId)
        .orElse(null);
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
        .state(OrderStateEnum.PROCESSING.getStringValue())
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
    boolean hasRushDeliveryProduct = false;

    for (OrderMedia orderMedia : order.getOrderMediaList()) {
      if (orderMedia.getMedia().getIsAbleToRushDelivery()) {
        orderMedia.setIsOrderForRushDelivery(true);
        hasRushDeliveryProduct = true;
      }
    }

    if (!hasRushDeliveryProduct) {
      throw new NotSupportRushDeliveryException();
    }

    // data coupling
    RushOrder rushOrder = createRushOrder(order, input);
    rushOrderRepository.save(rushOrder);
  }

  private void deleteOrderMediaFromRepository(OrderMedia orderMedia, Order order) {
    orderMediaRepository.delete(orderMedia);
    order.removeOrderMedia(orderMedia);
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
    Double total =
        deliveryFee != null ? calculationService.calculateTotal(order.getSubtotal(), order.getVat(),
            deliveryFee) : calculationService.calculateTotal(order.getSubtotal(), order.getVat());
    order.setTotal(total);

    orderRepository.save(order);
  }

  private void updateOrderMediaQuantity(OrderMedia orderMedia, Order order, Integer quantity) {
    if (quantity == 0) {
      deleteOrderMediaFromRepository(orderMedia, order);
    } else {
      orderMedia.setQuantity(quantity);
      orderMediaRepository.save(orderMedia);
    }
  }

}

// Design principle
// - SRP: Không thoả mãn, vì lớp làm nhiều nhiệm vụ
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác
// - DIP: Không thoả mãn, vì phụ thuộc vào các lớp service khác