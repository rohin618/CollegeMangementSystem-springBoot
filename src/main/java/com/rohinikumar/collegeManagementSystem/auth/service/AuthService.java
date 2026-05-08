package com.rohinikumar.collegeManagementSystem.auth.service;

import com.rohinikumar.collegeManagementSystem.auth.dto.ChangePasswordRequest;
import com.rohinikumar.collegeManagementSystem.auth.dto.LoginRequest;
import com.rohinikumar.collegeManagementSystem.auth.dto.LoginResponse;
import com.rohinikumar.collegeManagementSystem.auth.jwt.JwtService;
import com.rohinikumar.collegeManagementSystem.user.entity.User;
import com.rohinikumar.collegeManagementSystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username or password");
        }

        var userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtService.generateToken(userDetails);

        User user = userRepository
                .findByUsernameOrEmail(
                        request.getUsername(),
                        request.getUsername()
                )
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .passwordChanged(user.getPasswordChanged())
                .build();
    }

    public String changePassword(
            String username,
            ChangePasswordRequest request
    ) {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        if (!passwordEncoder.matches(
                request.getOldPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        user.setPasswordChanged(true);

        userRepository.save(user);

        return "Password changed successfully";
    }
}