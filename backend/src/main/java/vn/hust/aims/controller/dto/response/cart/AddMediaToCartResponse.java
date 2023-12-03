package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;

@Data
@SuperBuilder
public class AddMediaToCartResponse {

  private String message;

  public static AddMediaToCartResponse from(AddMediaToCartOutput output) {
    return AddMediaToCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
