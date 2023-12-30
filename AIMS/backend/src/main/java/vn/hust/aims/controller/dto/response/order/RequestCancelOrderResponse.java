package vn.hust.aims.controller.dto.response.order;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.order.RequestCancelOrderOutput;

@Data
@SuperBuilder
public class RequestCancelOrderResponse {

  private String message;

  public static RequestCancelOrderResponse from(RequestCancelOrderOutput output) {
    return RequestCancelOrderResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
