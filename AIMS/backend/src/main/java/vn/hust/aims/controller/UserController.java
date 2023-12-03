package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService userService;
  // TODO: CRUD người dùng
}
