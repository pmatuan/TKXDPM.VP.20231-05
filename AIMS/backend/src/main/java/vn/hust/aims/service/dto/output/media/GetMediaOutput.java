package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.entity.media.Media;

@Builder
@Data
public class GetMediaOutput {
    private Media media;
}
