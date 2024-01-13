package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class UpdateDeleteLimitExceededException extends AimsException {
    public UpdateDeleteLimitExceededException() {
        super(null, ErrorCodeList.UPDATE_DELETE_LIMIT_EXCEEDED, HttpStatus.BAD_REQUEST);
    }
}
