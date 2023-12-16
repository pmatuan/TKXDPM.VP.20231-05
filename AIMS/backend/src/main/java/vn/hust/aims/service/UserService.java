package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.aims.constant.UserRole;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.user.User;
import vn.hust.aims.exception.InvalidRoleException;
import vn.hust.aims.exception.NullEmailException;
import vn.hust.aims.exception.NullPasswordException;
import vn.hust.aims.exception.UserNotFoundException;
import vn.hust.aims.repository.user.UserRepository;
import vn.hust.aims.service.dto.input.user.*;
import vn.hust.aims.service.dto.output.order.GetAllOrderOutput;
import vn.hust.aims.service.dto.output.user.*;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
        validateCreateUserInput(input.getEmail(), input.getPassword(), input.getRole());

        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .password(input.getPassword()) // TODO: hash password
                .role(UserRole.from(input.getRole()))
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

        user.setName(input.getName() != null ? input.getName() : user.getName());
        user.setEmail(input.getEmail() != null ? input.getEmail() : user.getEmail());
        user.setPhoneNumber(input.getPhoneNumber() != null ? input.getPhoneNumber() : user.getPhoneNumber());

        if (validateRole(input.getRole())) {
            user.setRole(UserRole.from(input.getRole()));
        }

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

        userRepository.save(user);

        return user;
    }

    public ChangeBlockedStateOutput changeBlockedState(Long userId, Integer isBlocked) {
        return ChangeBlockedStateOutput.from(changeBlockedStateToDb(userId, isBlocked));
    }

    private Long changeBlockedStateToDb(Long userId, Integer isBlocked) {
        User user = getUserById(userId);

        user.setIsBlocked(isBlocked);

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

    private void validateCreateUserInput(String email, String password, String role) {
        validateEmail(email);
        validatePassword(password);
        validateRole(role);
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new NullEmailException();
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new NullPasswordException();
        }
    }

    private boolean validateRole(String role) {
        if (role != null && (UserRole.from(role) == UserRole.UNKNOWN)) {
            throw new InvalidRoleException();
        }

        return true;
    }
}
