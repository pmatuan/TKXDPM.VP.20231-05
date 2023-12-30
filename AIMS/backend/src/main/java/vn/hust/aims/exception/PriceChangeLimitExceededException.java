package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

public class PriceChangeLimitExceededException extends AimsException {
    public PriceChangeLimitExceededException() {
        super(null, ErrorCodeList.PRICE_CHANGE_LIMIT_EXCEEDED, HttpStatus.BAD_REQUEST);
    }
}
