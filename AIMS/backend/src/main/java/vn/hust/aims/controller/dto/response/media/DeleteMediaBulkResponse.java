package vn.hust.aims.controller.dto.response.media;

import lombok.Builder;
import lombok.Data;
import vn.hust.aims.service.dto.output.media.DeleteMediaBulkOutput;

@Data
@Builder
public class DeleteMediaBulkResponse {
    private final String message;

    public static DeleteMediaBulkResponse from(DeleteMediaBulkOutput deleteMediaBulkOutput) {
        return DeleteMediaBulkResponse.builder().message(deleteMediaBulkOutput.getMessage()).build();
    }
}
