package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.user.ChangeUserPasswordOutput;

@Data
@SuperBuilder
public class ChangeUserPasswordResponse {
    private Long userId;

    public static ChangeUserPasswordResponse from(ChangeUserPasswordOutput output) {
        return ChangeUserPasswordResponse.builder()
                .userId(output.getUserId())
                .build();
    }
}
