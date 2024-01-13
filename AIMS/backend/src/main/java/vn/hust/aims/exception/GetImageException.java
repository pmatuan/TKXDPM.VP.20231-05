package vn.hust.aims.exception;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public class GetImageException extends AimsException {
    public GetImageException() {
        super(null, ErrorCodeList.MEDIA_IMAGE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
