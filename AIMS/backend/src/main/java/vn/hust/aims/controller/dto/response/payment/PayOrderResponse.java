package vn.hust.aims.controller.dto.response.payment;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

@Data
@SuperBuilder
public class PayOrderResponse {

  private String url;

  private Object response;

  public static PayOrderResponse from(PayOrderOutput output) {
    return PayOrderResponse.builder()
        .url(output.getUrl())
        .response(output.getResponse())
        .build();
  }
}
