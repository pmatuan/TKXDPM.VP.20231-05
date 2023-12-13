package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.response.cancelorder.CancelOrderResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.OrderService;
import vn.hust.aims.service.dto.input.cancelorder.CancelOrderInput;
import vn.hust.aims.service.dto.output.cancelorder.CancelOrderOutput;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/cancel-order")
public class CancelOrderController {

  private final OrderService orderService;

  @GetMapping("/{orderId}")
  public ResponseEntity<AimsCommonResponse<Object>> cancelOrder(@PathVariable String orderId) {

    CancelOrderOutput output = orderService.cancelOrder(
        CancelOrderInput.builder()
            .orderId(orderId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        CancelOrderResponse.from(output)
    );
  }
}
