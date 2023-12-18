package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.media.Media;

import java.util.List;


@Builder
@Data
public class GetAllMediaOutput {
    private Page<Media> mediaPage;
}
