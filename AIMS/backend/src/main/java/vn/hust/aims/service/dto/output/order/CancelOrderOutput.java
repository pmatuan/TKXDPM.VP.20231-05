package vn.hust.aims.service.dto.output.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderOutput {
  private String url;
  public static CancelOrderOutput from(String url) {
    return CancelOrderOutput.builder()
        .url(url)
        .build();
  }
}
