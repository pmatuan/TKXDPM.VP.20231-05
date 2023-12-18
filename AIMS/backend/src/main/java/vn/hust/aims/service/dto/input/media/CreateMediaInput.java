package vn.hust.aims.service.dto.input.media;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.media.factory.MediaType;

@Data
@SuperBuilder
public class CreateMediaInput {
    private String jsonPayload;

    private MediaType mediaType;
}
