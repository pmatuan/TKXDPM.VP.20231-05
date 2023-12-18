package vn.hust.aims.service.dto.input.media;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class GetAllMediaInput {
    Map<String, Object> filterMap;
    int size;
    int page;
    String type;
}
