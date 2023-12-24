package vn.hust.aims.service.dto.input.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DeleteMediaInCartInput {
  private String cartId;
  private Long cartMediaId;
}
