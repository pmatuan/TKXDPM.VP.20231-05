package vn.hust.aims.controller.dto.response.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.service.dto.output.media.UpdateMediaOutput;

@Data
@Builder
public class UpdateMediaResponse {
    private String message;

    public static UpdateMediaResponse from(UpdateMediaOutput updateMediaOutput) {
        return UpdateMediaResponse.builder().message(updateMediaOutput.getMessage()).build();
    }
}
