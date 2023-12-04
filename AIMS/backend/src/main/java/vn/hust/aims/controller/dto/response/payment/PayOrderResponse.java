package vn.hust.aims.controller.dto.response.payment;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

@Data
@SuperBuilder
public class PayOrderResponse {

  private String url;

  private Object response;

  // PayOrderResponse - PayOrderOutput: Data coupling
  // Dữ liệu được truyền vừa đủ từ PayOrderOutput sang PayOrderResponse để tạo response cho client.
  public static PayOrderResponse from(PayOrderOutput output) {
    return PayOrderResponse.builder()
        .url(output.getUrl())
        .response(output.getResponse())
        .build();
  }
}
