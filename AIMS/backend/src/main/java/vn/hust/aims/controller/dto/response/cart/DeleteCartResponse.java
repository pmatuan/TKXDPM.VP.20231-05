package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;

@Data
@SuperBuilder
public class DeleteCartResponse {

  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static DeleteCartResponse from(DeleteCartOutput output) {
    return DeleteCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
