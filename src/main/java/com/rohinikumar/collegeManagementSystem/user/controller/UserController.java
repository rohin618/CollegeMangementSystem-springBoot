package com.rohinikumar.collegeManagementSystem.user.controller;

import com.rohinikumar.collegeManagementSystem.user.dto.*;
import com.rohinikumar.collegeManagementSystem.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(
            Authentication authentication,
            @Valid @RequestBody CreateUserRequest request
    ) {
        return userService.createUser(
                request,
                authentication.getName()
        );
    }

    @PostMapping("/bulk")
    public List<UserResponse> createUsers(
            Authentication authentication,
            @Valid @RequestBody BulkCreateUserRequest request
    ) {
        return userService.createUsers(
                request,
                authentication.getName()
        );
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return userService.updateUser(
                id,
                request,
                authentication.getName()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return userService.deleteUser(
                id,
                authentication.getName()
        );
    }

    @GetMapping
    public List<UserResponse> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }


    @GetMapping("/pagination")
    public UserPageResponse getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search
    ) {
        return userService.getUsers(
                page,
                size,
                search
        );
    }
}