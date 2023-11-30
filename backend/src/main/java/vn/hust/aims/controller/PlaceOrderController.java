package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.placeorder.CreateOrderRequest;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.PlaceOrderService;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;
import vn.hust.aims.utils.ResponseUtil;
import vn.hust.aims.controller.dto.response.placeorder.CreateOrderResponse;

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
}
