package vn.hust.aims.service.dto.input.media;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Map;

@Data
@SuperBuilder
public class UpdateMediaInput {
    private Long authorId;
    private Long MediaId;
    private Map<String, Object> mediaInfo;
}
