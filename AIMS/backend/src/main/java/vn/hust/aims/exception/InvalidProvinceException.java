package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidProvinceException extends AimsException{

  public InvalidProvinceException() {
    super(null, ErrorCodeList.INVALID_PROVINCE, HttpStatus.BAD_REQUEST);
  }
}
