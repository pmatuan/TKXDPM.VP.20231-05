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
import vn.hust.aims.controller.dto.response.placeorder.GetOrderResponse;
import vn.hust.aims.controller.dto.response.placeorder.UpdateMediaInOrderResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.PlaceOrderService;
import vn.hust.aims.service.dto.input.placeorder.DeleteMediaInOrderInput;
import vn.hust.aims.service.dto.input.placeorder.GetOrderInput;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.DeleteMediaInOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.GetOrderOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;
import vn.hust.aims.service.dto.output.placeorder.UpdateMediaInOrderOutput;
import vn.hust.aims.utils.ResponseUtil;
import vn.hust.aims.controller.dto.response.placeorder.CreateOrderResponse;
import vn.hust.aims.controller.dto.response.placeorder.UpdateDeliveryInfoResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/place-order")
public class PlaceOrderController {

  private final PlaceOrderService placeOrderService;

  @PostMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> createOrderFromCart(
      @RequestBody CreateOrderRequest request) {

    CreateOrderOutput output = placeOrderService.createOrderFromCart(request.toInput());

    return ResponseUtil.toSuccessCommonResponse(
        CreateOrderResponse.from(output)
    );
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<AimsCommonResponse<Object>> getOrder(@PathVariable String orderId) {

    GetOrderOutput output = placeOrderService.getOrder(
        GetOrderInput.builder()
            .orderId(orderId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        GetOrderResponse.from(output)
    );
  }

  @PutMapping("/{orderId}/delivery-info")
  public ResponseEntity<AimsCommonResponse<Object>> updateDeliveryInfo(
      @PathVariable String orderId, @RequestBody UpdateDeliveryInfoRequest request) {

    UpdateDeliveryInfoOutput output = placeOrderService.updateDeliveryInfo(
        request.toInput(orderId));

    return ResponseUtil.toSuccessCommonResponse(
        UpdateDeliveryInfoResponse.from(output)
    );
  }

  @PutMapping("/{orderId}/order-media/{orderMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> updateMediaInOrder(
      @PathVariable String orderId, @PathVariable Long orderMediaId,
      @RequestBody UpdateMediaInOrderRequest request) {

    UpdateMediaInOrderOutput output = placeOrderService.updateOrderMedia(
        request.toInput(orderId, orderMediaId));

    return ResponseUtil.toSuccessCommonResponse(
        UpdateMediaInOrderResponse.from(output)
    );
  }

  @DeleteMapping("/{orderId}/order-media/{orderMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> deleteOrderMedia(
      @PathVariable String orderId, @PathVariable Long orderMediaId) {

    DeleteMediaInOrderOutput output = placeOrderService.deleteOrderMedia(
        DeleteMediaInOrderInput.builder()
            .orderId(orderId)
            .orderMediaId(orderMediaId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        DeleteMediaInOrderResponse.from(output)
    );
  }

}
