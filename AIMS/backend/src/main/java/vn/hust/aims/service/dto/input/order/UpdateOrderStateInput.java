package vn.hust.aims.service.dto.input.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.OrderStateEnum;

@Data
@SuperBuilder
public class UpdateOrderStateInput {

  private String orderId;
  private OrderStateEnum state;
}
