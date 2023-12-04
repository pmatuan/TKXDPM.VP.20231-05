package vn.hust.aims.subsystem.dto.input;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PayOrderInput {
  private Double amount;
  private String message;
}
