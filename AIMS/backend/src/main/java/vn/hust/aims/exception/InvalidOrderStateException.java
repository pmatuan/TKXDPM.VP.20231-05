package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidOrderStateException extends AimsException{

  public InvalidOrderStateException() {
    super(null, ErrorCodeList.INVALID_ORDER_STATE, HttpStatus.BAD_REQUEST);
  }
}
