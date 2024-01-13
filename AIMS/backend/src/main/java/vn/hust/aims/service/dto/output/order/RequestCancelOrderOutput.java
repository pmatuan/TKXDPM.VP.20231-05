package vn.hust.aims.service.dto.output.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCancelOrderOutput {

  private String message;

  public static RequestCancelOrderOutput from(String message) {
    return RequestCancelOrderOutput.builder()
        .message(message)
        .build();
  }
}
