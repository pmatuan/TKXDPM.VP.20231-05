package vn.hust.aims.service.dto.output.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMediaInCartOutput {

  private String message;

  // from: data coupling
  // Chỉ truyền vừa đủ dữ liệu đầu vào
  public static DeleteMediaInCartOutput from(String message) {
    return DeleteMediaInCartOutput.builder()
        .message(message)
        .build();
  }
}
