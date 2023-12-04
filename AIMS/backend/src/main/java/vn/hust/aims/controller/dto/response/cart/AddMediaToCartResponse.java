package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;

// data-coupling
@Data
@SuperBuilder
public class AddMediaToCartResponse {

  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static AddMediaToCartResponse from(AddMediaToCartOutput output) {
    return AddMediaToCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
