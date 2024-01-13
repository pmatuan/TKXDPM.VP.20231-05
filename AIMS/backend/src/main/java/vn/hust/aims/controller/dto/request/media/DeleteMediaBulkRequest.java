package vn.hust.aims.controller.dto.request.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.media.DeleteMediaBulkInput;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMediaBulkRequest {
    private Long authorId;
    private List<Long> ids;

    public DeleteMediaBulkInput toInput() {
        return DeleteMediaBulkInput.builder()
                .authorId(authorId)
                .ids(this.ids)
                .build();
    }
}
