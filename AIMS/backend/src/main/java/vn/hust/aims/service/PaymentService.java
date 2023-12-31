package vn.hust.aims.service;

import vn.hust.aims.service.dto.input.payment.RefundInput;
import vn.hust.aims.service.dto.output.payment.PayOrderOutput;
import vn.hust.aims.service.dto.input.payment.PayOrderInput;

public interface PaymentService {

  PayOrderOutput payOrder(PayOrderInput input);

  void refund(RefundInput input);
}
