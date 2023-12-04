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

  private PaymentSubsystem paymentSubsystem;

  @PostMapping("/payorder")
  public ResponseEntity<AimsCommonResponse<Object>> payOrder(HttpServletRequest request,
      @RequestBody PayOrderRequest payOrderRequest) {

    paymentSubsystem = PaymentSubsystemFactory.get(
        ProviderType.from(payOrderRequest.getProvider()));

    PayOrderOutput output = paymentSubsystem.payOrder(request, payOrderRequest.toInput());

    return ResponseUtil.toSuccessCommonResponse(
        PayOrderResponse.from(output)
    );
  }
}
