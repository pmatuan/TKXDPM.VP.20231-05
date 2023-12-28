/*
  Functional cohesion: hầu hết các thành phần của lớp này đều hướng đến việc thực hiện một trách nhiệm chung rõ ràng là tính toán hóa đơn. Mỗi phương thức sử dụng kết quả của phương thức khác theo một quy trình.
*/

package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.placeorder.CreateOrderRequest;
import vn.hust.aims.controller.dto.request.placeorder.UpdateDeliveryInfoRequest;
import vn.hust.aims.controller.dto.request.placeorder.UpdateMediaInOrderRequest;
import vn.hust.aims.controller.dto.response.placeorder.DeleteMediaInOrderResponse;
import vn.hust.aims.controller.dto.response.placeorder.UpdateMediaInOrderResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.OrderService;
import vn.hust.aims.service.dto.input.placeorder.DeleteMediaInOrderInput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.DeleteMediaInOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateMediaInOrderOutput;
import vn.hust.aims.utils.ResponseUtil;
import vn.hust.aims.controller.dto.response.placeorder.CreateOrderResponse;
import vn.hust.aims.controller.dto.response.placeorder.UpdateDeliveryInfoResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/place-order")
public class PlaceOrderController {

  private final OrderService orderService;

  @PostMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> createOrderFromCart(
      @RequestBody CreateOrderRequest request) {

    // data coupling
    CreateOrderOutput output = orderService.createOrderFromCart(request.toInput());

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        CreateOrderResponse.from(output)
    );
  }

  @PutMapping("/{orderId}/delivery-info")
  public ResponseEntity<AimsCommonResponse<Object>> updateDeliveryInfo(
      @PathVariable String orderId, @RequestBody UpdateDeliveryInfoRequest request) {

    // data coupling
    UpdateDeliveryInfoOutput output = orderService.updateDeliveryInfo(
        request.toInput(orderId));

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        UpdateDeliveryInfoResponse.from(output)
    );
  }

  @PutMapping("/{orderId}/order-media/{orderMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> updateMediaInOrder(
      @PathVariable String orderId, @PathVariable Long orderMediaId,
      @RequestBody UpdateMediaInOrderRequest request) {

    // data coupling
    UpdateMediaInOrderOutput output = orderService.updateOrderMedia(
        request.toInput(orderId, orderMediaId));

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        UpdateMediaInOrderResponse.from(output)
    );
  }

  @DeleteMapping("/{orderId}/order-media/{orderMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> deleteOrderMedia(
      @PathVariable String orderId, @PathVariable Long orderMediaId) {

    // data coupling
    DeleteMediaInOrderOutput output = orderService.deleteOrderMedia(
        DeleteMediaInOrderInput.builder()
            .orderId(orderId)
            .orderMediaId(orderMediaId)
            .build()
    );

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        DeleteMediaInOrderResponse.from(output)
    );
  }

}
