package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class NullEmailException extends AimsException {
    public NullEmailException() {
        super(null, ErrorCodeList.NULL_EMAIL, HttpStatus.BAD_REQUEST);
    }
}
