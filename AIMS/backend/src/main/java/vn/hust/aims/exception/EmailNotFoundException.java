package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends AimsException {
    public EmailNotFoundException() {
        super(null, ErrorCodeList.EMAIL_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
