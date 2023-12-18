package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPasswordOutput {
    private Long userId;

    public static ChangeUserPasswordOutput from(Long userId) {
        return ChangeUserPasswordOutput.builder()
                .userId(userId)
                .build();
    }
}
