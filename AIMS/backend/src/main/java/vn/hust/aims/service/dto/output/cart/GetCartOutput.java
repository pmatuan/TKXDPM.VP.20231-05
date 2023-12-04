package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.cart.Cart;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetCartOutput {

  private Cart cart;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static GetCartOutput from(Cart cart) {
    return GetCartOutput.builder()
        .cart(cart)
        .build();
  }
}
