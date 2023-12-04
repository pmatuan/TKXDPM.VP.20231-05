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

    Cart cart = output.getCart();
    List<CartMedia> cartMediaList = cart.getCartMediaList();

    Double subtotal = calculateSubtotal(cartMediaList);

    Double VAT = calculateVAT(subtotal);

    return GetCartResponse.builder()
        .cart(cart)
        .subtotal(subtotal)
        .VAT(VAT)
        .total(subtotal + VAT)
        .build();
  }

  private static Double calculateSubtotal(List<CartMedia> cartMediaList) {

    Double subtotal = 0.0;

    for (CartMedia cartMedia : cartMediaList) {
      subtotal += cartMedia.getMedia().getPrice() * cartMedia.getQuantity();
    }

    return subtotal;
  }

  private static Double calculateVAT(Double subtotal) {
    // VAT is 10% of the subtotal
    return subtotal / 10;
  }
}
