package vn.hust.aims.controller.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hust.aims.service.dto.input.user.ChangeUserPasswordInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPasswordRequest {
    private Long userId;
    private String password;

    public ChangeUserPasswordInput toInput() {
        return ChangeUserPasswordInput.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
