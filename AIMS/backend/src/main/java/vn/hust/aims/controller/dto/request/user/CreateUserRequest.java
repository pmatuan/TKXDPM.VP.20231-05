package vn.hust.aims.controller.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.constant.UserRole;
import vn.hust.aims.service.dto.input.user.CreateUserInput;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    public CreateUserInput toInput() {
        return CreateUserInput.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .role(role)
                .build();
    }
}
