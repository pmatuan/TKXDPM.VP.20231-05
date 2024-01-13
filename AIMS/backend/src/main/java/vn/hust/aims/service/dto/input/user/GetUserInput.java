package vn.hust.aims.service.dto.input.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetUserInput {
    private Long userId;
}
