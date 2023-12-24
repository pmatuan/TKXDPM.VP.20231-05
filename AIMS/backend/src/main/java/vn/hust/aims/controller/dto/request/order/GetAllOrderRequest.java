package vn.hust.aims.controller.dto.request.order;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

@Data
@ParameterObject
public class GetAllOrderRequest {
  private Integer page;

  private Integer size;
}
