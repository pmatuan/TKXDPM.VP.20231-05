package vn.hust.aims.controller.dto.response.media.lp;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.lp.UpdateLPOutput;

@Data
@SuperBuilder
@Builder
public class UpdateLPResponse {

    private String message;

    public static UpdateLPResponse from(UpdateLPOutput output) {
        return UpdateLPResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
