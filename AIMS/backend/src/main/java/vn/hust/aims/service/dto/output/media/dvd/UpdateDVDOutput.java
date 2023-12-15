package vn.hust.aims.service.dto.output.media.dvd;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateDVDOutput {
    private String message;

    public static UpdateDVDOutput from(String message) {
        return UpdateDVDOutput.builder()
                .message(message)
                .build();
    }
}
