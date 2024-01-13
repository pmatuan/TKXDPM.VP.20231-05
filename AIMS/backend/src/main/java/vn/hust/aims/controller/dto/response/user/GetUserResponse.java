package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.user.User;
import vn.hust.aims.service.dto.output.user.GetUserOutput;

@Data
@SuperBuilder
public class GetUserResponse {
    private User user;

    public static GetUserResponse from(GetUserOutput output) {
        return GetUserResponse.builder()
                .user(output.getUser())
                .build();
    }
}
