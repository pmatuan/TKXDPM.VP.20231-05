package vn.hust.aims.controller.dto.request.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.placeorder.CreateOrderInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

  private String cartId;

  public CreateOrderInput toInput() {
    return CreateOrderInput.builder()
        .cartId(cartId)
        .build();
  }
}
