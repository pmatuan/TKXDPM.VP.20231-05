package vn.hust.aims.service.dto.input.payment;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PayOrderInput {
  private String orderId;
  private String provider;
}
