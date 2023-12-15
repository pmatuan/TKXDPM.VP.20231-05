package vn.hust.aims.service.dto.output.media.book;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateBookOutput {
    private String message;

    public static UpdateBookOutput from(String message) {

        return UpdateBookOutput.builder()
                .message(message)
                .build();
    }
}
