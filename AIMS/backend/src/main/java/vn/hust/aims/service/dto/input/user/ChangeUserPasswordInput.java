package vn.hust.aims.service.dto.input.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChangeUserPasswordInput {
    private Long userId;
    private String password;
}
