package vn.hust.aims.service;

import vn.hust.aims.service.dto.input.login.LoginInput;
import vn.hust.aims.service.dto.output.login.LoginOutput;

public interface LoginService {
  // Check login credentials and return user role
  LoginOutput checkLoginCredendtials(LoginInput input);
}
