package vn.hust.aims.service.dto.input.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateUserInput {
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
}
