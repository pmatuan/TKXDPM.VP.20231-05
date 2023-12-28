package vn.hust.aims.service.dto.input.payment.paypal;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PaypalTransactionInput {
  private String id;
  private String orderId;
}
