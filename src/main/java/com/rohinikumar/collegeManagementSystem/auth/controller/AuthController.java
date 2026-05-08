package com.rohinikumar.collegeManagementSystem.auth.controller;

import com.rohinikumar.collegeManagementSystem.auth.dto.ChangePasswordRequest;
import com.rohinikumar.collegeManagementSystem.auth.dto.LoginRequest;
import com.rohinikumar.collegeManagementSystem.auth.dto.LoginResponse;
import com.rohinikumar.collegeManagementSystem.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }

    @PostMapping("/change-password")
    public String changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request
    ) {

        return authService.changePassword(
                authentication.getName(),
                request
        );
    }
}