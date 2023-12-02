package vn.hust.aims.service.dto.input.cart;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateMediaInCartInput {
  private String cartId;
  private Long cartMediaId;
  private Integer quantity;
}
