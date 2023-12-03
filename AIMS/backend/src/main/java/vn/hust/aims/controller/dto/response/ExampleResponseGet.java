package vn.hust.aims.controller.dto.response;

import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
import vn.hust.aims.service.dto.output.ExampleGetOutput;

@Data
@SuperBuilder
public class ExampleResponseGet {

  List<ExampleGetOutput> content;

  Integer totalPages;

  Long totalElements;

  Integer size;

  public static ExampleResponseGet from(Page<ExampleGetOutput> page) {
    return ExampleResponseGet.builder()
        .content(page.getContent())
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .size(page.getSize())
        .build();
  }
}
