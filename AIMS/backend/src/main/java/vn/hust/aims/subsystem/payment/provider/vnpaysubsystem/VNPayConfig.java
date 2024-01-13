package vn.hust.aims.subsystem.payment.provider.vnpaysubsystem;

import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VNPayConfig {

  private String vnp_Version;
  private String vnp_Command;
  private String vnp_TmnCode;
  private String vnp_CurrCode;
  private String vnp_IpAddr;
  private String vnp_Locale;
  private String vnp_TxnRef;
  private String vnp_HashSecret;
  private String vnp_PayUrl;
  private String vnp_RefundUrl;

  public VNPayConfig(String vnp_TmnCode, String vnp_HashSecret) {
    this.vnp_Version = "2.1.0";
    this.vnp_Command = "pay";
    this.vnp_TmnCode = vnp_TmnCode;
    this.vnp_CurrCode = "VND";
    this.vnp_IpAddr = "127.0.0.1";
    this.vnp_Locale = "vn";
    this.vnp_TxnRef = getRandomNumber(8);
    this.vnp_HashSecret = vnp_HashSecret;
    this.vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    this.vnp_RefundUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";
  }

  public String getRandomNumber(int len) {
    Random rnd = new Random();
    String chars = "0123456789";
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
  }
}
