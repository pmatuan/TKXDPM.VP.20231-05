package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotSupportedException extends AimsException{

  public PaymentNotSupportedException() {
    super(null, ErrorCodeList.PAYMENT_NOT_SUPPORTED, HttpStatus.BAD_REQUEST);
  }
}
