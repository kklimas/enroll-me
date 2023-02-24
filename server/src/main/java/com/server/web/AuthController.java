package com.server.web;

import com.server.auth.AuthService;
import com.server.auth.dto.AuthResponseDTO;
import com.server.auth.dto.UserLoginDTO;
import com.server.auth.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public AuthResponseDTO login(@RequestBody UserLoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("register")
    public AuthResponseDTO register(@RequestBody UserRegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }
}
