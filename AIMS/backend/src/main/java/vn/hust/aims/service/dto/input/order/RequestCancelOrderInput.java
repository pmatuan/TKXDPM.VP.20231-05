package vn.hust.aims.service.dto.input.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RequestCancelOrderInput {
  private String orderId;
}
