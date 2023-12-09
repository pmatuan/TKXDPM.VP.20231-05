package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class CartMediaNotFoundException extends AimsException{

  public CartMediaNotFoundException() {
    super(null, ErrorCodeList.CART_MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST);
  }
}
