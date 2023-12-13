package vn.hust.aims.service.dto.input.cancelorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CancelOrderInput {
  private String orderId;
}
