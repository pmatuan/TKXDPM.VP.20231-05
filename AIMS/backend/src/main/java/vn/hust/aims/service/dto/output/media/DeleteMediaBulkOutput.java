package vn.hust.aims.service.dto.output.media;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteMediaBulkOutput {
    private final String message;

    public static DeleteMediaBulkOutput from(String message) {
        return DeleteMediaBulkOutput.builder().message(message).build();
    }
}
