package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class OrderMediaNotFoundException extends AimsException{

  public OrderMediaNotFoundException() {
    super(null, ErrorCodeList.ORDER_MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST);
  }
}
