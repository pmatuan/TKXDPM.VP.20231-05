package vn.hust.aims.controller.dto.response.media.dvd;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.dvd.UpdateDVDOutput;

@Data
@SuperBuilder
@Builder
public class UpdateDVDResponse {

    private String message;

    public static UpdateDVDResponse from(UpdateDVDOutput output) {
        return UpdateDVDResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
