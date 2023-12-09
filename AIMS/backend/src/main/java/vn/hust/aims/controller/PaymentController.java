package vn.hust.aims.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.payment.PayOrderRequest;
import vn.hust.aims.controller.dto.response.payment.PayOrderResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.subsystem.PaymentSubsystem;
import vn.hust.aims.subsystem.PaymentSubsystemFactory;
import vn.hust.aims.subsystem.ProviderType;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@NoArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này thực hiện các hoạt động liên quan đến thanh toán.
  // Mọi phương thức và thuộc tính đều được thiết kế để hỗ trợ chức năng thanh toán.

  private PaymentSubsystem paymentSubsystem;

  // PaymentController - PayOrderRequest: Data coupling
  // Lớp PaymentController nhận dữ liệu từ client đến hệ thống qua đối tượng PayOrderRequest .
  // Dữ liệu được truyền chỉ chứa những thông tin cần thiết.
  @PostMapping("/payorder")
  public ResponseEntity<AimsCommonResponse<Object>> payOrder(HttpServletRequest request,
      @RequestBody PayOrderRequest payOrderRequest) {

    // PaymentController - PaymentSubsystemFactory: Data coupling
    // PaymentController truyền ProviderType trong PayOrderRequest sang PaymentSubsystemFactory để xác định loại dịch vụ thanh toán cần thực hiện.
    paymentSubsystem = PaymentSubsystemFactory.get(
        ProviderType.from(payOrderRequest.getProvider()));

    // PaymentController - PaymentSubsystem: Data coupling
    // PaymentController truyền dữ liệu vừa đủ sang lớp PaymentSubsystem thông qua phương thức payOrder.
    PayOrderOutput output = paymentSubsystem.payOrder(request, payOrderRequest.toInput());

    // PaymentController - PayOrderResponse: Data coupling
    // PaymentController truyền dữ liệu vừa đủ cho lớp PayOrderResponse để tạo thành phản hồi cho client.
    return ResponseUtil.toSuccessCommonResponse(
        PayOrderResponse.from(output)
    );
  }
}
