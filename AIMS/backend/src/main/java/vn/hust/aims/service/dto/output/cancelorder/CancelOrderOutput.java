package vn.hust.aims.service.dto.output.cancelorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderOutput {

  private String message;

  public static CancelOrderOutput from(String message) {
    return CancelOrderOutput.builder()
        .message(message)
        .build();
  }
}
