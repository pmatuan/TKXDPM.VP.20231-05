package vn.hust.aims.service.dto.output.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderOutput {

  private String url;

  public static PayOrderOutput from(String url) {
    return PayOrderOutput.builder()
        .url(url)
        .build();
  }

}
