package vn.hust.aims.controller.dto.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
import vn.hust.aims.entity.user.User;
import vn.hust.aims.service.dto.output.user.GetAllUsersOutput;

import java.util.List;

@Data
@SuperBuilder
public class GetAllUsersResponse {
    private List<User> userList;
    private Integer numberOfPages;
    private Long totalNumberOfUsers;
    private Integer size;

    public static GetAllUsersResponse from(GetAllUsersOutput output) {
        Page<User> page = output.getPage();

        return GetAllUsersResponse.builder()
                .userList(page.getContent())
                .numberOfPages(page.getTotalPages())
                .totalNumberOfUsers(page.getTotalElements())
                .size(page.getSize())
                .build();
    }
}
