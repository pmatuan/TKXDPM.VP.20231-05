package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class NullPasswordException extends AimsException {
    public NullPasswordException() {
        super(null, ErrorCodeList.NULL_PASSWORD, HttpStatus.BAD_REQUEST);
    }
}
