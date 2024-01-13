/*
SOLID analysis:
 - Single Responsibility: this class is responsible for both handling HTTP requests and interacting with the UserService for business logic. It also handles different responsibilities for CRUD of users. However, further consideration is needed on whether or not to divide this class into smaller controllers.
 - Open - close principle: This class is not defined with extension in mind. It is also relatively closed for modification as each method is responsible for a specific user-related operation.
 - Liskov substitution principle: This class is not defined for inheritance.
 - Interface segregation principle: will be looked at on a project level
 - Dependency inversion principle: will be looked at on a project level
 */

package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.user.ChangeUserPasswordRequest;
import vn.hust.aims.controller.dto.request.user.CreateUserRequest;
import vn.hust.aims.controller.dto.request.user.UpdateUserRequest;
import vn.hust.aims.controller.dto.response.user.*;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.UserService;
import vn.hust.aims.service.dto.input.user.GetUserInput;
import vn.hust.aims.service.dto.output.user.*;
import vn.hust.aims.utils.PageableUtils;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<AimsCommonResponse<Object>> getUser(@PathVariable Long userId) {
    GetUserOutput output = userService.getUser(
            GetUserInput.builder()
                    .userId(userId)
                    .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
            GetUserResponse.from(output)
    );
  }

  @GetMapping("/all")
  public ResponseEntity<AimsCommonResponse<Object>> getAllUsers(
          @RequestParam(name = "page") Integer page,
          @RequestParam(name = "size") Integer size
  ) {
    Pageable pageable = PageableUtils.generate(page, size, "");
    GetAllUsersOutput output = userService.getAllUsers(pageable);

    return ResponseUtil.toSuccessCommonResponse(
            GetAllUsersResponse.from(output)
    );
  }

  @PostMapping("/create")
  public ResponseEntity<AimsCommonResponse<Object>> createUser(@RequestBody CreateUserRequest request) {
    CreateUserOutput output = userService.createUser(request.toInput());

    return ResponseUtil.toSuccessCommonResponse(
            CreateUserResponse.from(output)
    );
  }

  @PutMapping("/changePassword")
  public ResponseEntity<AimsCommonResponse<Object>> changePassword(@RequestBody ChangeUserPasswordRequest request) {
    ChangeUserPasswordOutput output = userService.changeUserPassword(request.toInput());

    return ResponseUtil.toSuccessCommonResponse(
            ChangeUserPasswordResponse.from(output)
    );
  }

  @PutMapping("/update/{userId}")
  public ResponseEntity<AimsCommonResponse<Object>> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
    UpdateUserOutput output = userService.updateUser(userId, request.toInput());

    return ResponseUtil.toSuccessCommonResponse(
            UpdateUserResponse.from(output)
    );
  }

  @PutMapping("/changeBlockedState")
  public ResponseEntity<AimsCommonResponse<Object>> changeBlockedState(
          @RequestParam(name = "userId") Long userId,
          @RequestParam(name = "setIsBlocked") Integer isBlocked
  ) {
    ChangeBlockedStateOutput output = userService.changeBlockedState(userId, isBlocked);

    return ResponseUtil.toSuccessCommonResponse(
            ChangeBlockedStateResponse.from(output)
    );
  }

  @DeleteMapping("/delete")
  public ResponseEntity<AimsCommonResponse<Object>> deleteUser(
          @RequestParam(name = "userId") Long userId
  ) {
    DeleteUserOutput output = userService.deleteUser(userId);

    return ResponseUtil.toSuccessCommonResponse(
            DeleteUserResponse.from(output)
    );
  }
}
