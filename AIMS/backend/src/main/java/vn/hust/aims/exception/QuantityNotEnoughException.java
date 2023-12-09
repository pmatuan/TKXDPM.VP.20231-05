package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class QuantityNotEnoughException extends AimsException{

  public QuantityNotEnoughException() {
    super(null, ErrorCodeList.QUANTITY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
  }
}
