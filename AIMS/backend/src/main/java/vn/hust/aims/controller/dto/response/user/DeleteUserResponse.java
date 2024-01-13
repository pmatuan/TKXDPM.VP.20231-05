package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.user.DeleteUserOutput;

@Data
@SuperBuilder
public class DeleteUserResponse {
    private Long userId;

    public static DeleteUserResponse from(DeleteUserOutput output) {
        return DeleteUserResponse.builder()
                .userId(output.getUserId())
                .build();
    }
}
