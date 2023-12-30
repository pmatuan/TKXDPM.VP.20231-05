package vn.hust.aims.controller.dto.request.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.login.LoginInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;

    public LoginInput toInput() {
        return LoginInput.builder()
                .email(email)
                .password(password)
                .build();
    }
}
