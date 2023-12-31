package vn.hust.aims.controller.dto.response.payment;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.payment.PayOrderOutput;

@Data
@SuperBuilder
public class PayOrderResponse {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này chứa các thuộc tính và phương thức liên quan chặt chẽ đến chức năng biểu diễn dữ liệu
  // đầu ra từ quá trình thanh toán và cung cấp phương thức chuyển đổi từ PayOrderOutput.

  private String url;

  // PayOrderResponse - PayOrderOutput: Data coupling
  // Dữ liệu được truyền vừa đủ từ PayOrderOutput sang PayOrderResponse để tạo response cho client.
  public static PayOrderResponse from(PayOrderOutput output) {
    return PayOrderResponse.builder()
        .url(output.getUrl())
        .build();
  }
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, lớp này chỉ chịu trách nhiệm biểu diễn dữ liệu đầu ra của thanh toán và cung cấp phương thức chuyển đổi từ PayOrderOutput.