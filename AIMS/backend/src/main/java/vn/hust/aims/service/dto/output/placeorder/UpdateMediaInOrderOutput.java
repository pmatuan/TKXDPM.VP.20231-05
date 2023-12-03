package vn.hust.aims.service.dto.output.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMediaInOrderOutput {

  private String message;

  public static UpdateMediaInOrderOutput from(String message) {
    return UpdateMediaInOrderOutput.builder()
        .message(message)
        .build();
  }
}
