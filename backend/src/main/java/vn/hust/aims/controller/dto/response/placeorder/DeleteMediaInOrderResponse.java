package vn.hust.aims.controller.dto.response.placeorder;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.cart.DeleteMediaInCartResponse;
import vn.hust.aims.service.dto.output.placeorder.DeleteMediaInOrderOutput;

@Data
@SuperBuilder
public class DeleteMediaInOrderResponse {

  private String message;

  public static DeleteMediaInCartResponse from(DeleteMediaInOrderOutput output){
    return DeleteMediaInCartResponse.builder()
        .message(output.getMessage())
        .build();
  }
}
