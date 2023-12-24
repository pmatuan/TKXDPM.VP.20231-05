package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidBlockedStateException extends AimsException{
    public InvalidBlockedStateException() {
        super(null, ErrorCodeList.INVALID_BLOCKED_STATE, HttpStatus.BAD_REQUEST);
    }
}
