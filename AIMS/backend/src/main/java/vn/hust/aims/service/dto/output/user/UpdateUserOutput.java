package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserOutput {
    private Long userId;

    public static UpdateUserOutput from(Long userId) {
        return UpdateUserOutput.builder()
                .userId(userId)
                .build();
    }
}
