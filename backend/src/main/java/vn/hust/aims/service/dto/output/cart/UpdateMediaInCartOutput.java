package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMediaInCartOutput {

  private String message;

  public static UpdateMediaInCartOutput from(String message) {
    return UpdateMediaInCartOutput.builder()
        .message(message)
        .build();
  }
}
