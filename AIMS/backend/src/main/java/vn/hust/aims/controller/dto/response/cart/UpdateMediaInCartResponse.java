package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.UpdateMediaInCartOutput;

@Data
@SuperBuilder
public class UpdateMediaInCartResponse {
  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static UpdateMediaInCartResponse from(UpdateMediaInCartOutput output){
    return UpdateMediaInCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
