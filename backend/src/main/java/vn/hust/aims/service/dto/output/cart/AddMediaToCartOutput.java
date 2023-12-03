package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddMediaToCartOutput {

  private String message;

  public static AddMediaToCartOutput from(String message) {
    return AddMediaToCartOutput.builder()
        .message(message)
        .build();
  }
}
