package vn.hust.aims.service.dto.output.media.book;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateBookOutput {
    private String message;

    public static CreateBookOutput from(String message) {

        return CreateBookOutput.builder()
                .message(message)
                .build();
    }
}
