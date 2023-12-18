package vn.hust.aims.controller.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.cart.AddMediaToCartInput;

// SOLID:
// Single Responsibility: Lớp này chỉ có duy nhất một lý do để thay đổi là đầu vào của request AddMediaToCart thay đổi
// Open Close: Nếu thêm trường vào request thì chỉ cần thêm vào lớp này
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMediaToCartRequest {

  private Long mediaId;
  private Integer quantity;

  // toInput: data coupling
  // Dữ liệu nhận vào cartId vừa đủ để xây dựng đối tượng AddMediaToCartInput
  public AddMediaToCartInput toInput(String cartId){
    return AddMediaToCartInput.builder()
        .cartId(cartId)
        .mediaId(this.mediaId)
        .quantity(this.quantity)
        .build();
  }
}
