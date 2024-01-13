package vn.hust.aims.controller.dto.request.order;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

@Data
@ParameterObject
public class GetAllOrderRequest {
  private Integer page;
  private Integer size;

  public GetAllOrderRequest() {
    this.page = 0;
    this.size = 30;
  }

  public GetAllOrderRequest(Integer page, Integer size) {
    this.page = page;
    this.size = size;
  }
}
