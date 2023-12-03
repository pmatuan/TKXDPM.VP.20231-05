package vn.hust.aims.controller.dto.response.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.placeorder.UpdateMediaInOrderOutput;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMediaInOrderResponse {

  private String message;

  public static UpdateMediaInOrderResponse from(UpdateMediaInOrderOutput output) {
    return UpdateMediaInOrderResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
