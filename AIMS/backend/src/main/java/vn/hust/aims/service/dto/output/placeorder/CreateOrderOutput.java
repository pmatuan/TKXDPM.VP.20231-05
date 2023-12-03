package vn.hust.aims.service.dto.output.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderOutput {

  private String orderId;

  public static CreateOrderOutput from(String orderId) {
    return CreateOrderOutput.builder()
        .orderId(orderId)
        .build();
  }
}
