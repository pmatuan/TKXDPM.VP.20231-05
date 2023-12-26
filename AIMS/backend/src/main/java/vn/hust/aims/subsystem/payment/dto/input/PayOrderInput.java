package vn.hust.aims.subsystem.payment.dto.input;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PayOrderInput {
  private Double amount;
  private String message;
}
