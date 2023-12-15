package vn.hust.aims.controller.dto.response.media.book;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.book.CreateBookOutput;

@Data
@SuperBuilder
@Builder
public class CreateBookResponse {
    private String message;

    public static CreateBookResponse from(CreateBookOutput output) {
        return CreateBookResponse.builder()
                .message(output.getMessage())
                .build();
    }
}
