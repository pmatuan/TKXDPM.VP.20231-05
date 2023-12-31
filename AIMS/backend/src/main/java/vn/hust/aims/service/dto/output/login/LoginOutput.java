package vn.hust.aims.service.dto.output.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutput {
    private Long id;
    private String role;
    private String status;

    public static LoginOutput from(Long id, String role) {
        return LoginOutput.builder()
                .id(id)
                .role(role)
                .build();
    }
}
