package vn.hust.aims.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.ExamplePostInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleRequestPost {

  @NotBlank
  @NotNull
  private String description;
  @NotBlank
  @NotNull
  private String createdBy;

  public ExamplePostInput toInput(){
    return ExamplePostInput.builder()
        .description(description)
        .createdBy(createdBy)
        .build();
  }
}
