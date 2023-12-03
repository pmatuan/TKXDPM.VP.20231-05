package vn.hust.aims.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.cart.UpdateMediaInCartInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMediaInCartRequest {

  private Integer quantity;

  public UpdateMediaInCartInput toInput(String cartId, Long cartMediaId){
    return UpdateMediaInCartInput.builder()
        .cartId(cartId)
        .cartMediaId(cartMediaId)
        .quantity(quantity)
        .build();
  }
}
