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

// Design principle
// - SRP: Thoả mãn, vì lớp làm 1 nhiệm vụ duy nhất là huỷ đơn hàng
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác
// - DIP: Không thoả mãn, vì phụ thuộc vào OrderService
