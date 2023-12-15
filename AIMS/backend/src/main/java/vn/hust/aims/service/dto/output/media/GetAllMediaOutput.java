package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.entity.media.Media;

import java.util.List;


@Builder
@Data
public class GetAllMediaOutput {
    private List<Media> mediaList;
}
