package vn.hust.aims.service.dto.input.payment;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.order.Order;

@Data
@SuperBuilder
public class RefundInput {
  private Order order;
}
