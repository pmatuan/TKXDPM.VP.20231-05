package vn.hust.aims.controller.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.enumeration.OrderStateEnum;
import vn.hust.aims.exception.InvalidOrderStateException;
import vn.hust.aims.service.dto.input.order.UpdateOrderStateInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStateRequest {

  private String state;

  public UpdateOrderStateInput toInput(String orderId) {
    validateState();
    return UpdateOrderStateInput.builder()
        .orderId(orderId)
        .state(OrderStateEnum.from(state))
        .build();
  }

  private void validateState() {
    if (state == null || OrderStateEnum.from(state).equals(OrderStateEnum.UNKNOWN)) {
      throw new InvalidOrderStateException();
    }
  }
}
