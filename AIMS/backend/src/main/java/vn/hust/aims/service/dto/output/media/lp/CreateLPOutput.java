package vn.hust.aims.service.dto.output.media.lp;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data@SuperBuilder
public class CreateLPOutput {
    private String message;

    public static CreateLPOutput from(String message) {

        return CreateLPOutput.builder()
                .message(message)
                .build();
    }
}
