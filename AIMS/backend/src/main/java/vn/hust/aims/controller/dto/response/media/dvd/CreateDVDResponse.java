package vn.hust.aims.controller.dto.response.media.dvd;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.dvd.CreateDVDOutput;

@Data
@SuperBuilder
public class CreateDVDResponse {
    private String message;

    public static CreateDVDResponse from(CreateDVDOutput output) {

        return CreateDVDResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
