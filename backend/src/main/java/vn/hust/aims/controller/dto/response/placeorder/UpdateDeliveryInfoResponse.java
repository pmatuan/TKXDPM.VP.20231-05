package vn.hust.aims.controller.dto.response.placeorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.placeorder.UpdateDeliveryInfoOutput;

@Data
@SuperBuilder
public class UpdateDeliveryInfoResponse {

  private String message;

  public static UpdateDeliveryInfoResponse from(UpdateDeliveryInfoOutput output) {
    return UpdateDeliveryInfoResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
