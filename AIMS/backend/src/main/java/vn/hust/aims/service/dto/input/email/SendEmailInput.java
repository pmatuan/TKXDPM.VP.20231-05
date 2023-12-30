package vn.hust.aims.service.dto.input.email;

import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.email.Param;

@Data
@SuperBuilder
public class SendEmailInput {
  private Boolean status;
  private String destination;
  private String templateName;
  private List<Param> params;
}
