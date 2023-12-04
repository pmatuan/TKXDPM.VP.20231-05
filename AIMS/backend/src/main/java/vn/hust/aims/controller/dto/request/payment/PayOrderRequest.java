package vn.hust.aims.controller.dto.request.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.subsystem.dto.input.PayOrderInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRequest {

  private Double amount;
  private String message;
  private String provider;

  // PayOrderRequest - PayOrderInput: Data coupling
  // Dữ liệu được truyền vừa đủ từ PayOrderRequest sang PayOrderInput để tạo input cho payment subsystem sau đó
  public PayOrderInput toInput() {
    return PayOrderInput.builder()
        .amount(amount)
        .message(message)
        .build();
  }
}
