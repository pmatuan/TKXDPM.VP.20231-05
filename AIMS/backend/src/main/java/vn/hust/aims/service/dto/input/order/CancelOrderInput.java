package vn.hust.aims.service.dto.input.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CancelOrderInput {
  private String orderId;
}
