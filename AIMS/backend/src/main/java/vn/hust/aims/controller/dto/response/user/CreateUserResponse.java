package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.user.CreateUserOutput;

@Data
@SuperBuilder
public class CreateUserResponse {
    private Long userId;

    public static CreateUserResponse from(CreateUserOutput output) {
        return CreateUserResponse.builder()
                .userId(output.getUserId())
                .build();
    }
}
