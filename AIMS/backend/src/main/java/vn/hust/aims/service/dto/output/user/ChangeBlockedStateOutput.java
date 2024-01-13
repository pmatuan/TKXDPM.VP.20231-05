package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBlockedStateOutput {
    private Long userId;

    public static ChangeBlockedStateOutput from(Long userId) {
        return ChangeBlockedStateOutput.builder()
                .userId(userId)
                .build();
    }
}
