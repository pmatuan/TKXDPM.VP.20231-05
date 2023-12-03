package vn.hust.aims.service.dto.input.placeorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetOrderInput {

  private String orderId;
}
