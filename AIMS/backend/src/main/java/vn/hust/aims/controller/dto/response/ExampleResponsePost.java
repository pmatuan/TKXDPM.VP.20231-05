package vn.hust.aims.controller.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.ExamplePostOutput;

@Data
@SuperBuilder
public class ExampleResponsePost {

  private String message;

  public static ExampleResponsePost from(ExamplePostOutput output) {
    return ExampleResponsePost.builder()
        .message(output.getMessage())
        .build();
  }
}
