package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMediaOutput {
    private String message;

    public static UpdateMediaOutput from(String message) {
        return UpdateMediaOutput.builder().message(message).build();
    }
}
