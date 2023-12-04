package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.DeleteMediaInCartOutput;

@Data
@SuperBuilder
public class DeleteMediaInCartResponse {

  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static DeleteMediaInCartResponse from(DeleteMediaInCartOutput output){
    return DeleteMediaInCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
