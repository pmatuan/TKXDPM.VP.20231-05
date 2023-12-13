package vn.hust.aims.controller.dto.response.cancelorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cancelorder.CancelOrderOutput;

@Data
@SuperBuilder
public class CancelOrderResponse {

  private String message;

  public static CancelOrderResponse from(CancelOrderOutput output) {
    return CancelOrderResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
