package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends AimsException{

  public InvalidEmailException() {
    super(null, ErrorCodeList.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
  }
}
