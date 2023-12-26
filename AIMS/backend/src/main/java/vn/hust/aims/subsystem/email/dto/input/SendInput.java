package vn.hust.aims.subsystem.email.dto.input;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SendInput {
  private String config;
  private String destination;
  private String title;
  private String content;
}
