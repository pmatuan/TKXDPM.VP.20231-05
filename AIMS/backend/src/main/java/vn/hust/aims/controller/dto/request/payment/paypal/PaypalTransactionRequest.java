package vn.hust.aims.controller.dto.request.payment.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.payment.paypal.PaypalTransactionInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaypalTransactionRequest {
  private String id;
  private String orderId;
  public PaypalTransactionInput toInput(){
    return PaypalTransactionInput.builder()
        .id(id)
        .orderId(orderId)
        .build();
  }
}
