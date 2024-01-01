package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.order.GetAllOrderRequest;
import vn.hust.aims.controller.dto.request.order.UpdateOrderStateRequest;
import vn.hust.aims.controller.dto.response.order.RequestCancelOrderResponse;
import vn.hust.aims.controller.dto.response.order.GetAllOrderResponse;
import vn.hust.aims.controller.dto.response.order.GetOrderResponse;
import vn.hust.aims.controller.dto.response.order.UpdateOrderStateResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.OrderService;
import vn.hust.aims.service.dto.input.order.CancelOrderInput;
import vn.hust.aims.service.dto.input.order.RequestCancelOrderInput;
import vn.hust.aims.service.dto.input.order.GetOrderInput;
import vn.hust.aims.service.dto.output.order.CancelOrderOutput;
import vn.hust.aims.service.dto.output.order.RequestCancelOrderOutput;
import vn.hust.aims.service.dto.output.order.GetAllOrderOutput;
import vn.hust.aims.service.dto.output.order.GetOrderOutput;
import vn.hust.aims.service.dto.output.order.UpdateOrderStateOutput;
import vn.hust.aims.utils.PageableUtils;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

  private final OrderService orderService;

  @GetMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> getAllOrder(GetAllOrderRequest request) {

    Pageable pageable = PageableUtils.generate(request.getPage(), request.getSize(), "");
    GetAllOrderOutput output = orderService.getAllOrder(pageable);

    return ResponseUtil.toSuccessCommonResponse(
        GetAllOrderResponse.from(output)
    );
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<AimsCommonResponse<Object>> getOrder(@PathVariable String orderId) {

    GetOrderOutput output = orderService.getOrder(
        GetOrderInput.builder()
            .orderId(orderId)
            .build()
    );

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        GetOrderResponse.from(output)
    );
  }

  @GetMapping("/{orderId}/request-cancel")
  public ResponseEntity<AimsCommonResponse<Object>> requestCancelOrder(@PathVariable String orderId) {

    RequestCancelOrderOutput output = orderService.requestCancelOrder(
        RequestCancelOrderInput.builder()
            .orderId(orderId)
            .build()
    );

    // data coupling
    return ResponseUtil.toSuccessCommonResponse(
        RequestCancelOrderResponse.from(output)
    );
  }

  @GetMapping("/{orderId}/cancel")
  public String cancelOrder(@PathVariable String orderId) {

    CancelOrderOutput output = orderService.cancelOrder(
        CancelOrderInput.builder()
            .orderId(orderId)
            .build()
    );

    return "redirect:" + output.getUrl();
  }

  @PutMapping("/{orderId}/state")
  public ResponseEntity<AimsCommonResponse<Object>> updateOrderState(
      @PathVariable String orderId, @RequestBody UpdateOrderStateRequest request) {

    UpdateOrderStateOutput output = orderService.updateOrderState(request.toInput(orderId));

    return ResponseUtil.toSuccessCommonResponse(
        UpdateOrderStateResponse.from(output)
    );
  }
}

// Design principle
// - SRP: Thoả mãn, vì lớp làm 1 nhiệm vụ duy nhất là expose các api liên quan đến order
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác
// - DIP: Không thoả mãn, vì phụ thuộc vào OrderService
