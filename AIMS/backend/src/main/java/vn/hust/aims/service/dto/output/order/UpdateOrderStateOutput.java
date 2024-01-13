package vn.hust.aims.service.dto.output.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStateOutput {

  private String message;

  public static UpdateOrderStateOutput from(String message) {
    return UpdateOrderStateOutput.builder()
        .message(message)
        .build();
  }
}
