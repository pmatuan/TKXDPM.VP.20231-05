package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class InvalidRoleException extends AimsException {
    public InvalidRoleException() {
        super(null, ErrorCodeList.INVALID_ROLE, HttpStatus.BAD_REQUEST);
    }
}
