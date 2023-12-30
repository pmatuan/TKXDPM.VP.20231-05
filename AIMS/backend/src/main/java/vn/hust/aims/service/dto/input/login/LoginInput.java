package vn.hust.aims.service.dto.input.login;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LoginInput {
    private String email;
    private String password;
}
