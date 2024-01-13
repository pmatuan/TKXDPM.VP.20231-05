package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends AimsException{

  public OrderNotFoundException() {
    super(null, ErrorCodeList.ORDER_NOT_FOUND, HttpStatus.BAD_REQUEST);
  }
}
