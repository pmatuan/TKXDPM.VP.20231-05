package vn.hust.aims.controller.dto.response.order;

import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.service.dto.output.order.GetAllOrderOutput;

@Data
@SuperBuilder
public class GetAllOrderResponse {

  private List<Order> content;

  private Integer totalPages;

  private Long totalElements;

  private Integer size;

  public static GetAllOrderResponse from(GetAllOrderOutput output){
    Page<Order> page = output.getPage();

    return GetAllOrderResponse.builder()
        .content(page.getContent())
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .size(page.getSize())
        .build();
  }
}
