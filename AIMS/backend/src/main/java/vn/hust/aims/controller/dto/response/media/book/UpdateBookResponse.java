package vn.hust.aims.controller.dto.response.media.book;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.media.book.UpdateBookOutput;

@Data
@SuperBuilder
@Builder
public class UpdateBookResponse {

        private String message;

        public static UpdateBookResponse from(UpdateBookOutput output) {
            return UpdateBookResponse.builder()
                    .message(output.getMessage())
                    .build();
        }
}

