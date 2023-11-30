package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;

@Data
@SuperBuilder
public class GetCartResponse {

  private Cart cart;

  public static GetCartResponse from(GetCartOutput output) {
    return GetCartResponse.builder()
        .cart(output.getCart())
        .build();
  }
}
