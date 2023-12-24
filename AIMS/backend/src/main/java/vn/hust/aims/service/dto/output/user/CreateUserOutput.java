package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserOutput {
    private Long userId;

    public static CreateUserOutput from(Long userId) {
        return CreateUserOutput.builder()
                .userId(userId)
                .build();
    }
}
