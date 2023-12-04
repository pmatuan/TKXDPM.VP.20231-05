package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddMediaToCartOutput {

  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static AddMediaToCartOutput from(String message) {
    return AddMediaToCartOutput.builder()
        .message(message)
        .build();
  }
}
