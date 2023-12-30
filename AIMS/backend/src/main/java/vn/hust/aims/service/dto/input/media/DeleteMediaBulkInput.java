package vn.hust.aims.service.dto.input.media;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeleteMediaBulkInput {
    private Long authorId;
    List<Long> ids;
}
