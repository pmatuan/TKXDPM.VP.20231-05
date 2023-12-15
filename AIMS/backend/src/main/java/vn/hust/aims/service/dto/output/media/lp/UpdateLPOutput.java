package vn.hust.aims.service.dto.output.media.lp;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateLPOutput {
    private String message;

    public static UpdateLPOutput from(String message) {

        return UpdateLPOutput.builder()
                .message(message)
                .build();
    }
}
