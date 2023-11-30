package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;

@Data
@SuperBuilder
public class CreateCartResponse {

  private String cartId;

  public static CreateCartResponse from(CreateCartOutput output) {
    return CreateCartResponse.builder()
        .cartId(output.getCartId())
        .build();
  }
}
