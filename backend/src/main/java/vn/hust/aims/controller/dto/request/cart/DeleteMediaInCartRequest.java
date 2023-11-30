package vn.hust.aims.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.cart.DeleteMediaInCartInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMediaInCartRequest {

  public DeleteMediaInCartInput toInput(Long cartMediaId) {
    return DeleteMediaInCartInput.builder()
        .cartMediaId(cartMediaId)
        .build();
  }
}
