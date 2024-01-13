package vn.hust.aims.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.user.User;
import vn.hust.aims.exception.EmailNotFoundException;
import vn.hust.aims.exception.NullEmailException;
import vn.hust.aims.exception.WrongPasswordException;
import vn.hust.aims.repository.user.UserRepository;
import vn.hust.aims.service.LoginService;
import vn.hust.aims.service.dto.input.login.LoginInput;
import vn.hust.aims.service.dto.output.login.LoginOutput;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginOutput checkLoginCredendtials(LoginInput input) {
        if (input.getEmail().isBlank()) {
            throw new NullEmailException();
        }

        User user = userRepository.findByEmail(input.getEmail())
            .orElseThrow(EmailNotFoundException::new);

        if (!input.getPassword().equals(user.getPassword())) {
            throw new WrongPasswordException();
        }

        return LoginOutput.from(user.getId(), user.getRole());
    }
}
