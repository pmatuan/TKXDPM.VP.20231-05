package vn.hust.aims.controller.dto.response.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.order.UpdateOrderStateOutput;

@Data
@SuperBuilder
public class UpdateOrderStateResponse {

  private String message;

  public static UpdateOrderStateResponse from(UpdateOrderStateOutput output) {
    return UpdateOrderStateResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
