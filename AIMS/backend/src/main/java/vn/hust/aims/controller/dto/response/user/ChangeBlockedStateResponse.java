package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.output.user.ChangeBlockedStateOutput;

@Data
@SuperBuilder
public class ChangeBlockedStateResponse {
    private Long userId;

    public static ChangeBlockedStateResponse from(ChangeBlockedStateOutput output) {
        return ChangeBlockedStateResponse.builder()
                .userId(output.getUserId())
                .build();
    }
}
