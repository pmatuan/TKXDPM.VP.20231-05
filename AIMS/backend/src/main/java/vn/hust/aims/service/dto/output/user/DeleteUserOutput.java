package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserOutput {
    private Long userId;

    public static DeleteUserOutput from(Long userId) {
        return DeleteUserOutput.builder()
                .userId(userId)
                .build();
    }
}
