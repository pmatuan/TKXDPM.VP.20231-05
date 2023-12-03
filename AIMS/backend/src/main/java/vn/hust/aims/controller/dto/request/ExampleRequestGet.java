package vn.hust.aims.controller.dto.request;

import java.time.Instant;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import vn.hust.aims.service.dto.input.ExampleGetInput;

@Data
@ParameterObject
public class ExampleRequestGet {

  private String description;

  private Instant createdFrom;

  private Instant createdTo;

  private String createdBy;

  private Integer page;

  private Integer size;

  public ExampleGetInput toInput() {
    return ExampleGetInput.builder()
        .description(this.getDescription())
        .createdFrom(this.getCreatedFrom())
        .createdTo(this.getCreatedTo())
        .createdBy(this.getCreatedBy())
        .build();
  }
}
