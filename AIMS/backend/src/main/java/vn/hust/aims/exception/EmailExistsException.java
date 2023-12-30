package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends AimsException {
    public EmailExistsException() {
        super(null, ErrorCodeList.EMAIL_EXISTS, HttpStatus.BAD_REQUEST);
    }
}
