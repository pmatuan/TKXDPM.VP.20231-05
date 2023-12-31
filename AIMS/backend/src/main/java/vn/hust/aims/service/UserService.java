package vn.hust.aims.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.user.ChangeUserPasswordInput;
import vn.hust.aims.service.dto.input.user.CreateUserInput;
import vn.hust.aims.service.dto.input.user.GetUserInput;
import vn.hust.aims.service.dto.input.user.UpdateUserInput;
import vn.hust.aims.service.dto.output.user.*;

public interface UserService {
  // GET
  GetUserOutput getUser(@NotNull GetUserInput input);
  GetAllUsersOutput getAllUsers(Pageable pageable);

  // CREATE
  CreateUserOutput createUser(CreateUserInput input);

  // UPDATE
  UpdateUserOutput updateUser(Long userId, UpdateUserInput input);
  ChangeUserPasswordOutput changeUserPassword(ChangeUserPasswordInput input);
  ChangeBlockedStateOutput changeBlockedState(Long userId, Integer isBlocked);

  // DELETE
  DeleteUserOutput deleteUser(Long userId);
}
