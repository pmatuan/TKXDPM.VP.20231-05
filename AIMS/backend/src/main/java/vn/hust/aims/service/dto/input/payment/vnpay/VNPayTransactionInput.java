package vn.hust.aims.service.dto.input.payment.vnpay;

import java.time.Instant;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class VNPayTransactionInput {
  private Boolean status;
  private String code;
  private String orderInfo;
  private Instant paymentTime;
  private String transactionId;
  private Double amount;
}
