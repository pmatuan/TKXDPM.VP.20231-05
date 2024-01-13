package vn.hust.aims.service;

import org.springframework.data.domain.Pageable;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.service.dto.input.order.CancelOrderInput;
import vn.hust.aims.service.dto.input.order.GetOrderInput;
import vn.hust.aims.service.dto.input.order.RequestCancelOrderInput;
import vn.hust.aims.service.dto.input.order.UpdateOrderStateInput;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;
import vn.hust.aims.service.dto.output.order.CancelOrderOutput;
import vn.hust.aims.service.dto.output.order.GetAllOrderOutput;
import vn.hust.aims.service.dto.output.order.GetOrderOutput;
import vn.hust.aims.service.dto.output.order.RequestCancelOrderOutput;
import vn.hust.aims.service.dto.output.order.UpdateOrderStateOutput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;

public interface OrderService {

  CreateOrderOutput createOrderFromCart(CreateOrderInput input);

  GetOrderOutput getOrder(GetOrderInput input);

  GetAllOrderOutput getAllOrder(Pageable pageable);

  UpdateDeliveryInfoOutput updateDeliveryInfo(UpdateDeliveryInfoInput input);

  UpdateOrderStateOutput updateOrderState(UpdateOrderStateInput input);

  RequestCancelOrderOutput requestCancelOrder(RequestCancelOrderInput input);

  CancelOrderOutput cancelOrder(CancelOrderInput input);

  Order getOrderById(String orderId);

  void saveOrder(Order order);

  String getCustomerEmailFromOrder(String orderId);
}
