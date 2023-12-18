package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMediaOutput {
    String message;

    public static CreateMediaOutput from(String message) {
        return CreateMediaOutput.builder().message(message).build();
    }
}
