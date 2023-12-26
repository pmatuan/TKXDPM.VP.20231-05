package vn.hust.aims.subsystem.payment.dto.output;

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
  private Object response;

  public static PayOrderOutput fromUrl (String url) {
    return PayOrderOutput.builder()
        .url(url)
        .build();
  }

  public static PayOrderOutput fromResponse(Object response) {
    return PayOrderOutput.builder()
        .response(response)
        .build();
  }

}
