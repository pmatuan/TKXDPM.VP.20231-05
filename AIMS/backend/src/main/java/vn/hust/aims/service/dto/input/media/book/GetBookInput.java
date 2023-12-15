package vn.hust.aims.service.dto.input.media.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetBookInput {
    private Long id;
}
