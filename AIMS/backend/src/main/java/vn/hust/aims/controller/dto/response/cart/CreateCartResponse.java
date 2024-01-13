package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;

// data-coupling
@Data
@SuperBuilder
public class CreateCartResponse {

  private String cartId;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static CreateCartResponse from(CreateCartOutput output) {
    return CreateCartResponse.builder()
        .cartId(output.getCartId())
        .build();
  }
}
