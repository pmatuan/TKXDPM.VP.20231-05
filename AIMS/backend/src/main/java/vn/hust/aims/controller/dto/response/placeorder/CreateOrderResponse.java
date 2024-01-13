package vn.hust.aims.controller.dto.response.placeorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.placeorder.CreateOrderOutput;

@Data
@SuperBuilder
public class CreateOrderResponse {

  private String orderId;

  public static CreateOrderResponse from(CreateOrderOutput output) {
    return CreateOrderResponse.builder()
        .orderId(output.getOrderId())
        .build();
  }
}
