package vn.hust.aims.controller.dto.request.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.placeorder.UpdateMediaInOrderInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMediaInOrderRequest {

  private Integer quantity;

  public UpdateMediaInOrderInput toInput(String orderId, Long orderMediaId) {
    return UpdateMediaInOrderInput.builder()
        .orderId(orderId)
        .orderMediaId(orderMediaId)
        .quantity(quantity)
        .build();
  }
}
