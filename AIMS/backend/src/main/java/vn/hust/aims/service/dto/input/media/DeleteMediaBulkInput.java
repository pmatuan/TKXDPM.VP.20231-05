package vn.hust.aims.service.dto.input.media;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeleteMediaBulkInput {
    List<Long> ids;
}
