package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends AimsException{

  public CartNotFoundException() {
    super(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST);
  }
}
