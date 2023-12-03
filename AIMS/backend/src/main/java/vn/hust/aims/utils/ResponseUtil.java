package vn.hust.aims.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.hust.aims.response.AimsCommonResponse;

public class ResponseUtil {
    public static ResponseEntity<AimsCommonResponse<Object>> toSuccessCommonResponse(Object data) {
        return new ResponseEntity<>(new AimsCommonResponse<>(data), HttpStatus.OK);
    }
}
