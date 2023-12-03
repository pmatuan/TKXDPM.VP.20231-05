package vn.hust.aims.service.dto.output.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.order.Order;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderOutput {

  private Order order;

  public static GetOrderOutput from(Order order) {
    return GetOrderOutput.builder()
        .order(order)
        .build();
  }
}
