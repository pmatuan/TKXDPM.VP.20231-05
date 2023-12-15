package vn.hust.aims.service.dto.output.media.dvd;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateDVDOutput {
    private String message;

    public static CreateDVDOutput from(String message) {

        return CreateDVDOutput.builder()
                .message(message)
                .build();
    }
}
