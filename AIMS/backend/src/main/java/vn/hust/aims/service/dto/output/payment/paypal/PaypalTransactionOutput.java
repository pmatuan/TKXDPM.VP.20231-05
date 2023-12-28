package vn.hust.aims.service.dto.output.payment.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaypalTransactionOutput {
  private String url;
  public static PaypalTransactionOutput from(String url){
    return PaypalTransactionOutput.builder()
        .url(url)
        .build();
  }
}
