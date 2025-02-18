package top.johnnycse.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.johnnycse.flight.dto.request.LoginRequest;
import top.johnnycse.flight.dto.request.RegisterRequest;
import top.johnnycse.flight.dto.response.UserLoginResponse;
import top.johnnycse.flight.service.User.UserService;
import top.johnnycse.flight.utils.ApiResponse;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest request) {
        UserLoginResponse response = userService.login(request.getUsername(), request.getPassword());
        return ApiResponse.ok(response);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return ApiResponse.ok("注册成功");
    }

}
