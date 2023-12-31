package vn.hust.aims.controller.dto.request.payment.vnpay;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import vn.hust.aims.constant.Constant;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.payment.vnpay.VNPayTransactionInput;

@Data
@ParameterObject
public class VNPayTransactionRequest {

  private String vnp_TransactionStatus;
  private String vnp_ResponseCode;
  private String vnp_OrderInfo;
  private String vnp_PayDate;
  private String vnp_TransactionNo;
  private String vnp_TxnRef;
  private Double vnp_Amount;

  public VNPayTransactionInput toInput(){
    return VNPayTransactionInput.builder()
        .status(vnp_TransactionStatus.equals("00") ? true : false)
        .code(vnp_ResponseCode)
        .orderInfo(vnp_OrderInfo)
        .paymentTime(convertToInstant(vnp_PayDate))
        .transactionId(vnp_TransactionNo + "-" + vnp_TxnRef)
        .amount(vnp_Amount)
        .build();
  }

  private Instant convertToInstant(String vnp_PayDate){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    LocalDateTime localDateTime = LocalDateTime.parse(vnp_PayDate, formatter);
    Instant instant = localDateTime.toInstant(Constant.VIETNAM_ZONE_OFFSET);
    return instant;
  }
}
