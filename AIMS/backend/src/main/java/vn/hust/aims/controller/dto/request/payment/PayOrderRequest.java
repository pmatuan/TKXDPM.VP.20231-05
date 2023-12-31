package vn.hust.aims.controller.dto.request.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.payment.PayOrderInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRequest {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này chứa các thuộc tính và phương thức liên quan chặt chẽ đến chức năng biểu diễn dữ liệu
  // đầu vào cho quá trình thanh toán và cung cấp phương thức chuyển đổi thành đối tượng PayOrderInput.
  private String orderId;
  private String provider;

  // PayOrderRequest - PayOrderInput: Data coupling
  // Dữ liệu được truyền vừa đủ từ PayOrderRequest sang PayOrderInput để tạo input cho payment subsystem sau đó
  public PayOrderInput toInput() {
    return PayOrderInput.builder()
        .orderId(orderId)
        .provider(provider)
        .build();
  }
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, lớp này chỉ chịu trách nhiệm biểu diễn dữ liệu đầu vào cho thanh toán và chuyển đổi thành đối tượng PayOrderInput.