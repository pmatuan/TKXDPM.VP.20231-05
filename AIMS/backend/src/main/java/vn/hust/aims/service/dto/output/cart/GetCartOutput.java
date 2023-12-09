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
  private Double subtotal;
  private Double VAT;
  private Double total;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static GetCartOutput from(Cart cart, Double subtotal, Double VAT, Double total) {
    return GetCartOutput.builder()
        .cart(cart)
        .subtotal(subtotal)
        .VAT(VAT)
        .total(total)
        .build();
  }
}
