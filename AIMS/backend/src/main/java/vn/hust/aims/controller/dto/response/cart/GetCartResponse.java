package vn.hust.aims.controller.dto.response.cart;

import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;

@Data
@SuperBuilder
public class GetCartResponse {

  private Cart cart;
  private Double subtotal;
  private Double VAT; // calculate the VAT amount directly instead of displaying the percentage
  private Double total;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static GetCartResponse from(GetCartOutput output) {

    return GetCartResponse.builder()
        .cart(output.getCart())
        .subtotal(output.getSubtotal())
        .VAT(output.getVAT())
        .total(output.getTotal())
        .build();
  }
}
