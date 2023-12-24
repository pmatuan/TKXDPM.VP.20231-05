package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidTimeException extends AimsException{

  public InvalidTimeException() {
    super(null, ErrorCodeList.INVALID_TIME, HttpStatus.BAD_REQUEST);
  }
}
