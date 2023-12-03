package vn.hust.aims.service.dto.output.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMediaInOrderOutput {

  private String message;

  public static DeleteMediaInOrderOutput from(String message) {
    return DeleteMediaInOrderOutput.builder()
        .message(message)
        .build();
  }
}
