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
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, vì chỉ chịu trách nhiệm về việc thực hiện thanh toán
// - OCP: thoả mãn, vì các lớp cụ thể có thể triển khai lại phương thức payOrder theo nhu cầu mà không làm thay đổi interface
// - ISP: thoả mãn, vì chỉ định đúng các phương thức cần thiết cho một chức năng cụ thể.