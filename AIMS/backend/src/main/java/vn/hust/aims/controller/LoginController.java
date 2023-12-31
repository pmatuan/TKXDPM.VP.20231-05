package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.login.LoginRequest;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.LoginService;
import vn.hust.aims.service.dto.output.login.LoginOutput;
import vn.hust.aims.utils.ResponseUtil;
import vn.hust.aims.controller.dto.response.login.LoginResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> login(
            @RequestBody LoginRequest request
            ) {
        LoginOutput output = loginService.checkLoginCredendtials(request.toInput());

        return ResponseUtil.toSuccessCommonResponse(
                LoginResponse.from(output)
        );
    }
}
