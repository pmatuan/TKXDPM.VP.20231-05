package vn.hust.aims.service.dto.input.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.constant.UserRole;

@Data
@SuperBuilder
public class CreateUserInput {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
}
