package vn.hust.aims.subsystem.payment;

// PaymentSubsystem - PayOrderInput: Data coupling
// Interface PaymentSubsystem chỉ định phương thức payOrder nhận vào một đối tượng HttpServletRequest và PayOrderInput.
// Dữ liệu được truyền từ PayOrderInput sang PaymentSubsystem thông qua phương thức payOrder

import vn.hust.aims.entity.order.Order;

// Mức độ cohesion: Functional Cohesion
public interface PaymentSubsystem {
  String payOrder(Double amount, String message);
  String refund(Order order);
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, vì chỉ chịu trách nhiệm về việc thực hiện thanh toán
// - OCP: thoả mãn, vì các lớp cụ thể có thể triển khai lại phương thức payOrder theo nhu cầu mà không làm thay đổi interface
// - ISP: thoả mãn, vì chỉ định đúng các phương thức cần thiết cho một chức năng cụ thể.