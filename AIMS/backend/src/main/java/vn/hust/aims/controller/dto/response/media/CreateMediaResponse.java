package vn.hust.aims.controller.dto.response.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.service.dto.output.media.CreateMediaOutput;

@Data
@Builder
public class CreateMediaResponse {
    String message;

    public static CreateMediaResponse from(CreateMediaOutput createMediaOutput) {
        return CreateMediaResponse.builder().message(createMediaOutput.getMessage()).build();
    }
}
