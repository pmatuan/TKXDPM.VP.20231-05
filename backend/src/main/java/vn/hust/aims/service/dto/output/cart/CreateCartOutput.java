package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartOutput {
  private String cartId;
  public static CreateCartOutput from(String cartId) {
    return CreateCartOutput.builder()
        .cartId(cartId)
        .build();
  }
}
