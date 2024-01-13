package vn.hust.aims.service.dto.output.payment.vnpay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VNPayTransactionOutput {
  private String url;
  public static VNPayTransactionOutput from(String url){
    return VNPayTransactionOutput.builder()
        .url(url)
        .build();
  }
}
