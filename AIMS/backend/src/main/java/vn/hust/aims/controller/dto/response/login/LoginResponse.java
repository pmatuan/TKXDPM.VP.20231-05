package vn.hust.aims.controller.dto.response.login;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.login.LoginOutput;

@Data
@SuperBuilder
public class LoginResponse {
    private Long id;
    private String role;

    public static LoginResponse from(LoginOutput output) {
        return LoginResponse.builder()
                .id(output.getId())
                .role(output.getRole())
                .build();
    }
}
