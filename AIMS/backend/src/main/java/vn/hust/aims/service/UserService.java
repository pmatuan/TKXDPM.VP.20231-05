/*
SOLID analysis:
 - Single Responsibility: this class has a clear responsibility related to user-related operations. While not directly part of user operations, validation methods are closely related and supports the primary responsibility. In the future, separating validation methods may be considered if more validation is required.
 - Open - close principle: this class is relatively closed for modification. Each method is responsible for a specific user-related operation, and the implementation details are encapsulated within each method.
 - Liskov substitution principle: This class is not defined for inheritance.
 - Interface segregation principle: will be looked at on a project level
 - Dependency inversion principle: will be looked at on a project level
 */

package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.aims.constant.UserRole;

import vn.hust.aims.entity.email.Param;
import vn.hust.aims.entity.user.User;
import vn.hust.aims.exception.*;
import vn.hust.aims.repository.user.UserRepository;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.user.ChangeUserPasswordInput;
import vn.hust.aims.service.dto.input.user.CreateUserInput;
import vn.hust.aims.service.dto.input.user.GetUserInput;
import vn.hust.aims.service.dto.input.user.UpdateUserInput;

import vn.hust.aims.entity.user.User;
import vn.hust.aims.exception.*;
import vn.hust.aims.repository.user.UserRepository;
import vn.hust.aims.service.dto.input.user.*;

import vn.hust.aims.service.dto.output.user.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailService mailService;

    //-------------GET----------------

    public GetUserOutput getUser(@NotNull GetUserInput input) {
        User user = getUserById(input.getUserId());

        return GetUserOutput.from(user);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public GetAllUsersOutput getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return GetAllUsersOutput.from(page);
    }

    //----------------CREATE----------------

    public CreateUserOutput createUser(CreateUserInput input) {
        User user = createUserToDatabase(input);

        return CreateUserOutput.from(user.getId());
    }

    private User createUserToDatabase(CreateUserInput input) {
        validateUserInput(input.getName(), input.getEmail(), input.getPassword(), input.getRole());

        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .password(input.getPassword()) // TODO: hash password
                .role(input.getRole())
                .build();

        userRepository.save(user);

        return user;
    }

    //----------------UPDATE---------------------

    public UpdateUserOutput updateUser(Long userId, UpdateUserInput input) {
        User user = updateUserToDatabase(userId, input);

        return UpdateUserOutput.from(user.getId());
    }

    private User updateUserToDatabase(Long userId, UpdateUserInput input) {
        User user = getUserById(userId);

        validateUserInput(input.getName(),  input.getRole());

        if (input.getEmail().isBlank()) {
            throw new NullEmailException();
        }

        User tmp = userRepository.findByEmail(input.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        if (tmp.getId() != userId) {
            throw new EmailExistsException();
        }

        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setRole(input.getRole());

        List<Param> params = Arrays.asList();

        mailService.send(
                SendEmailInput.builder()
                        .status(true)
                        .templateName("Cập nhật thông tin")
                        .destination(user.getEmail())
                        .params(params)
                        .build()
        );

        userRepository.save(user);

        return user;
    }

    public ChangeUserPasswordOutput changeUserPassword(ChangeUserPasswordInput input) {
        User user = changeUserPasswordToDb(input);

        return ChangeUserPasswordOutput.from(user.getId());
    }

    private User changeUserPasswordToDb(ChangeUserPasswordInput input) {
        User user = getUserById(input.getUserId());

        validatePassword(input.getPassword());
        user.setPassword(input.getPassword());

        List<Param> params = Arrays.asList();

        mailService.send(
                SendEmailInput.builder()
                        .status(true)
                        .templateName("Đổi mật khẩu")
                        .destination(user.getEmail())
                        .params(params)
                        .build()
        );

        userRepository.save(user);

        return user;
    }

    public ChangeBlockedStateOutput changeBlockedState(Long userId, Integer isBlocked) {
        return ChangeBlockedStateOutput.from(changeBlockedStateToDb(userId, isBlocked));
    }

    private Long changeBlockedStateToDb(Long userId, Integer isBlocked) {
        User user = getUserById(userId);

        validateIsBlocked(isBlocked);
        user.setIsBlocked(isBlocked);

        String blockedState;
        String action;

        if (isBlocked == 1) {
            blockedState = "bị chặn";
            action = "chặn";
        } else {
            blockedState = "được bỏ chặn";
            action = "bỏ chặn";
        }

        List<Param> params = Arrays.asList(
                Param.builder().key("blockedState").value(blockedState).build(),
                Param.builder().key("action").value(action).build()
        );

        mailService.send(
                SendEmailInput.builder()
                        .status(true)
                        .templateName("Trạng thái tài khoản")
                        .destination(user.getEmail())
                        .params(params)
                        .build()
        );

        userRepository.save(user);

        return userId;
    }

    //-------------------DELETE------------------

    public DeleteUserOutput deleteUser(Long userId) {
        User user = deleteUserFromDatabase(userId);

        return DeleteUserOutput.from(user.getId());
    }

    private User deleteUserFromDatabase(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);

        return user;
    }

    //---------------VALIDATE-----------------

    private void validateUserInput(String name, String email, String password, String role) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        validateRole(role);
    }

    private void validateUserInput(String name, String role) {
        validateName(name);
        validateRole(role);
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new EmptyNameException();
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new NullEmailException();
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailExistsException();
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new NullPasswordException();
        }
    }

    private boolean validateRole(String role) {
        Set<String> roles = Collections.unmodifiableSet(
                new HashSet<String>(Arrays.asList(role.split(",")))
        );

        if (roles.isEmpty() || !UserRole.roles.containsAll(roles)) {
            throw new InvalidRoleException();
        }

        return true;
    }

    private void validateIsBlocked(Integer isBlocked) {
        if (isBlocked != 0 && isBlocked != 1) {
            throw new InvalidBlockedStateException();
        }
    }
}
