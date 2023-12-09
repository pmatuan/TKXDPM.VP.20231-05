package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class MediaNotFoundException extends AimsException{

  public MediaNotFoundException() {
    super(null, ErrorCodeList.MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST);
  }
}
