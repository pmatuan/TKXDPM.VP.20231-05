package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends AimsException {
    public WrongPasswordException() {
        super(null, ErrorCodeList.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
    }
}
