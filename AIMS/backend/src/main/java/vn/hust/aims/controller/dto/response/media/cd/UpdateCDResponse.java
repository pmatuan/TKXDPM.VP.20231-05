package vn.hust.aims.controller.dto.response.media.cd;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.cd.UpdateCDOutput;

@Data
@SuperBuilder
@Builder
public class UpdateCDResponse {
    private String message;

    public static UpdateCDResponse from(UpdateCDOutput output) {
        return UpdateCDResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
