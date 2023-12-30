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
    private String role;
    private String status;

    public static LoginOutput from(String role) {
        return LoginOutput.builder()
                .role(role)
                .build();
    }
}
