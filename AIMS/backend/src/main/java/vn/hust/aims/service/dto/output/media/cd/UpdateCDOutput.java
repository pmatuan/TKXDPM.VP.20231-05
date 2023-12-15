package vn.hust.aims.service.dto.output.media.cd;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateCDOutput {
    private String message;

    public static UpdateCDOutput from(String message) {

        return UpdateCDOutput.builder()
                .message(message)
                .build();
    }
}
