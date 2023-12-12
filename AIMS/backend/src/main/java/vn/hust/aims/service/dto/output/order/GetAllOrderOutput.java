package vn.hust.aims.service.dto.output.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.order.Order;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderOutput {

  private Page<Order> page;

  public static GetAllOrderOutput from(Page<Order> page) {
    return GetAllOrderOutput.builder()
        .page(page)
        .build();
  }
}
