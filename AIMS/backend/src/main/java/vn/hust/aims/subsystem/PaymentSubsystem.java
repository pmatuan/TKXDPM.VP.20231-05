package vn.hust.aims.subsystem;

import javax.servlet.http.HttpServletRequest;
import vn.hust.aims.subsystem.dto.input.PayOrderInput;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

public interface PaymentSubsystem {
  PayOrderOutput payOrder(HttpServletRequest request, PayOrderInput input);
}
