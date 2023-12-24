package vn.hust.aims.controller.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.user.UpdateUserInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String role;

    public UpdateUserInput toInput() {
        return UpdateUserInput.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }
}
