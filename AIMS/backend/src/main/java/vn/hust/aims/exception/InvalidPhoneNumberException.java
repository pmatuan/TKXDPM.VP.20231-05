package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidPhoneNumberException extends AimsException{

  public InvalidPhoneNumberException() {
    super(null, ErrorCodeList.INVALID_PHONE_NUMBER, HttpStatus.BAD_REQUEST);
  }
}
