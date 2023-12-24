package vn.hust.aims.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.cart.UpdateMediaInCartInput;


// SOLID:
// Single Responsibility: Lớp này chỉ có duy nhất một lý do để thay đổi là đầu vào của request UpdateMediaInCart thay đổi
// Open Close: Nếu thêm trường vào request thì chỉ cần thêm vào lớp này
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMediaInCartRequest {

  private Integer quantity;

  // toInput: data coupling
  // Dữ liệu nhận vào cartId và cartMediaId vừa đủ để xây dựng đối tượng UpdateMediaInCartInput
  public UpdateMediaInCartInput toInput(String cartId, Long cartMediaId){
    return UpdateMediaInCartInput.builder()
        .cartId(cartId)
        .cartMediaId(cartMediaId)
        .quantity(quantity)
        .build();
  }
}
