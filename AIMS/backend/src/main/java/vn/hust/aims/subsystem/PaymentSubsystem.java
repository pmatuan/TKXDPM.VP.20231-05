package vn.hust.aims.subsystem;

import javax.servlet.http.HttpServletRequest;
import vn.hust.aims.subsystem.dto.input.PayOrderInput;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

// PaymentSubsystem - PayOrderInput: Data coupling
// Interface PaymentSubsystem chỉ định phương thức payOrder nhận vào một đối tượng HttpServletRequest và PayOrderInput.
// Dữ liệu được truyền từ PayOrderInput sang PaymentSubsystem thông qua phương thức payOrder

// Mức độ cohesion: Functional Cohesion
public interface PaymentSubsystem {
  PayOrderOutput payOrder(HttpServletRequest request, PayOrderInput input);
}
