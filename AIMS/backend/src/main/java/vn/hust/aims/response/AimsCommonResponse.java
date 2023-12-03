package vn.hust.aims.response;

import java.io.Serializable;
import lombok.*;
import org.springframework.http.HttpStatus;
import vn.hust.aims.constant.Constant;
import vn.hust.aims.exception.ErrorCodeList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsCommonResponse<T> implements Serializable {

  private static final long serialVersionUID = -6669686067446636607L;

  protected String code;

  protected String message;

  protected T result;

  public AimsCommonResponse(T result) {
    this.code = Constant.SUCCESS_CODE;
    this.message = Constant.SUCCESS_MESSAGE;
    this.result = result;
  }

  public static AimsCommonResponse<Object> internalError() {
    return new AimsCommonResponse<>(
        Constant.INTERNAL_SERVER_ERROR_CODE,
        Constant.INTERNAL_SERVER_ERROR_MESSAGE,
        null
    );
  }

  public static AimsCommonResponse<Object> badRequest(String message) {
    return new AimsCommonResponse<>(
        HttpStatus.BAD_REQUEST.toString(),
        message,
        null
    );
  }

  public static AimsCommonResponse<Object> badRequest(String message, ErrorCodeList errorCode) {
    return new AimsCommonResponse<>(
        errorCode.toCode(),
        message,
        null
    );
  }

  public static AimsCommonResponse<Object> badRequest(ErrorCodeList errorCode, Object data) {
    return new AimsCommonResponse<>(
        errorCode.toCode(),
        errorCode.toString(),
        data
    );
  }

  public static AimsCommonResponse<Object> forbidden(String message) {
    return new AimsCommonResponse<>(
        HttpStatus.FORBIDDEN.toString(),
        message,
        null
    );
  }
}
