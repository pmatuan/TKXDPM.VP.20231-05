package vn.hust.aims.service.dto.input.media;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.MediaType;

import java.util.Map;

@Data
@SuperBuilder
public class CreateMediaInput {
    private Map<String, Object> mediaInfo;

    private MediaType mediaType;
}
