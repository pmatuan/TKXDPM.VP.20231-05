package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.user.User;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserOutput {
    private User user;

    public static GetUserOutput from(User user) {
        return GetUserOutput.builder()
                .user(user)
                .build();
    }
}
