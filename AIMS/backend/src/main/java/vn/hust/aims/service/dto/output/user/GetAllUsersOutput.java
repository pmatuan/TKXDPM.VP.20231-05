package vn.hust.aims.service.dto.output.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.user.User;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersOutput {
    private Page<User> page;

    public static GetAllUsersOutput from(Page<User> page) {
        return GetAllUsersOutput.builder()
                .page(page)
                .build();
    }
}
