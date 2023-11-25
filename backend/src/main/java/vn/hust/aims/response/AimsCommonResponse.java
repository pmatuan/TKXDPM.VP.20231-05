package vn.hust.aims.response;

import java.io.Serializable;
import lombok.*;
import vn.hust.aims.constant.Constant;


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
}
