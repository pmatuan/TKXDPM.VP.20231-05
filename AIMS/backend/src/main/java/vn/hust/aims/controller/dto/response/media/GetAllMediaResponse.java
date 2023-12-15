package vn.hust.aims.controller.dto.response.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.service.dto.output.media.GetAllMediaOutput;

import java.util.List;


@Builder
@Data
public class GetAllMediaResponse {

    private List<Media> mediaList;

    public static GetAllMediaResponse from(GetAllMediaOutput getAllMediaOutput) {
        return GetAllMediaResponse.builder().mediaList(getAllMediaOutput.getMediaList()).build();
    }
}
