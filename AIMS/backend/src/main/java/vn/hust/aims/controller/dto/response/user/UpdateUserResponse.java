package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.user.CreateUserOutput;
import vn.hust.aims.service.dto.output.user.UpdateUserOutput;

@Data
@SuperBuilder
public class UpdateUserResponse {
    private Long userId;

    public static UpdateUserResponse from(UpdateUserOutput output) {
        return UpdateUserResponse.builder()
                .userId(output.getUserId())
                .build();
    }
}
