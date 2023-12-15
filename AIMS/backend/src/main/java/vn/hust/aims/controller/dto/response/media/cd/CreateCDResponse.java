package vn.hust.aims.controller.dto.response.media.cd;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.media.book.CreateBookResponse;
import vn.hust.aims.service.dto.output.media.book.CreateBookOutput;
import vn.hust.aims.service.dto.output.media.cd.CreateCDOutput;


@Data
@SuperBuilder
public class CreateCDResponse {
    private String message;

    public static CreateCDResponse from(CreateCDOutput output) {
        return CreateCDResponse.builder()
                .message(output.getMessage())
                .build();

    }
}
