package vn.hust.aims.service.dto.output.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.RushOrder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderOutput {

  private Order order;
  private RushOrder rushOrder;

  public static GetOrderOutput from(Order order, RushOrder rushOrder) {
    return GetOrderOutput.builder()
        .order(order)
        .rushOrder(rushOrder)
        .build();
  }
}
