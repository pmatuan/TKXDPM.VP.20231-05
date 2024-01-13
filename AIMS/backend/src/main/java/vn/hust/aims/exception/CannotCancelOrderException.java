package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class CannotCancelOrderException extends AimsException{

  public CannotCancelOrderException() {
    super(null, ErrorCodeList.CANNOT_CHANGE_ORDER_STATE, HttpStatus.BAD_REQUEST);
  }
}

