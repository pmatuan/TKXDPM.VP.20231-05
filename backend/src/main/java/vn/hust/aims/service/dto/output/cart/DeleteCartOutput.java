package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCartOutput {

  private String message;

  public static DeleteCartOutput from(String message) {
    return DeleteCartOutput.builder()
        .message(message)
        .build();
  }
}
