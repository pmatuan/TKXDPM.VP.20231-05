package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class CannotChangeOrderStateException extends AimsException{

  public CannotChangeOrderStateException() {
    super(null, ErrorCodeList.CANNOT_CHANGE_ORDER_STATE, HttpStatus.BAD_REQUEST);
  }
}
