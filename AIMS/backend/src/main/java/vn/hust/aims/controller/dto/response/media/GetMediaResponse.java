package vn.hust.aims.controller.dto.response.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.service.dto.output.media.GetMediaOutput;

import java.time.Instant;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMediaResponse {
    private Media media;

    public static GetMediaResponse from(GetMediaOutput getMediaOutput) {
        return GetMediaResponse.builder().media(getMediaOutput.getMedia()).build();
    }
}
