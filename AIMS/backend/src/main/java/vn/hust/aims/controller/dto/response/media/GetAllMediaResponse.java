package vn.hust.aims.controller.dto.response.media;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.service.dto.output.media.GetAllMediaOutput;

import java.util.List;


@Builder
@Data
public class GetAllMediaResponse {

    private Page<Media> mediaPage;

    public static GetAllMediaResponse from(GetAllMediaOutput getAllMediaOutput) {
        return GetAllMediaResponse.builder().mediaPage(getAllMediaOutput.getMediaPage()).build();
    }
}
