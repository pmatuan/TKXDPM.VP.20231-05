package vn.hust.aims.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.cart.AddMediaToCartInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMediaToCartRequest {

  private Long mediaId;
  private Integer quantity;

  public AddMediaToCartInput toInput(String cartId){
    return AddMediaToCartInput.builder()
        .cartId(cartId)
        .mediaId(this.mediaId)
        .quantity(this.quantity)
        .build();
  }
}