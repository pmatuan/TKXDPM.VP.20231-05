package vn.hust.aims.controller.dto.response.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;

@Data
@SuperBuilder
public class DeleteCartResponse {

  private String message;

  public static DeleteCartResponse from(DeleteCartOutput output) {
    return DeleteCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
