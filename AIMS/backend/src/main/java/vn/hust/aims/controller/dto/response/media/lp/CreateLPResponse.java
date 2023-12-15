package vn.hust.aims.controller.dto.response.media.lp;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.lp.CreateLPOutput;

@Data
@SuperBuilder
public class CreateLPResponse {
    private String message;

    public static CreateLPResponse from(CreateLPOutput output) {
        return CreateLPResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
