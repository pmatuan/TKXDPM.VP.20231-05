package vn.hust.aims.service.dto.output.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryInfoOutput {

  private String message;

  public static UpdateDeliveryInfoOutput from(String message) {
    return UpdateDeliveryInfoOutput.builder()
        .message(message)
        .build();
  }
}
