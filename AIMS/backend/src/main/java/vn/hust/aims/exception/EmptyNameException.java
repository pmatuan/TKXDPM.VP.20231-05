package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class EmptyNameException extends AimsException {
    public EmptyNameException() {
        super(null, ErrorCodeList.EMPTY_NAME, HttpStatus.BAD_REQUEST);
    }
}
