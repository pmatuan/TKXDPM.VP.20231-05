package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AimsException {
    public UserNotFoundException() {
        super(null, ErrorCodeList.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
