package vn.hust.aims.controller.dto.response.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.service.dto.output.order.GetOrderOutput;

@Data
@SuperBuilder
public class GetOrderResponse {

  private Order order;

  public static GetOrderResponse from(GetOrderOutput output){
    return GetOrderResponse.builder()
        .order(output.getOrder())
        .build();
  }
}
