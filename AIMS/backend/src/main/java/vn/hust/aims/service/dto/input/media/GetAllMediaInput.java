package vn.hust.aims.service.dto.input.media;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllMediaInput {
    int size;
    int page;
}
