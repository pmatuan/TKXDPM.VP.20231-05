package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class NotSupportRushDeliveryException extends AimsException{

  public NotSupportRushDeliveryException() {
    super(null, ErrorCodeList.PROVINCE_NOT_SUPPORT_RUSH_DELIVERY, HttpStatus.BAD_REQUEST);
  }
}
