package vn.hust.aims.service.dto.input;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import vn.hust.aims.utils.StringUtil;

@Data
@Builder
public class ExampleGetInput {

  private String description;

  private Instant createdFrom;

  private Instant createdTo;

  private String createdBy;

  public void normalize() {
    description = StringUtil.formatStringForLikeQuery(description);
    createdBy = StringUtil.formatStringForLikeQuery(createdBy);
  }
}
