package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.PaymentService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
  private final PaymentService paymentService;
  // TODO: Thanh toán
}
