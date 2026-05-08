package com.rohinikumar.collegeManagementSystem.admin.controller;

import com.rohinikumar.collegeManagementSystem.admin.dto.CreateUserRequest;
import com.rohinikumar.collegeManagementSystem.user.dto.UserResponse;
import com.rohinikumar.collegeManagementSystem.user.entity.User;
import com.rohinikumar.collegeManagementSystem.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

//    private final UserService userService;
//
//    @PostMapping
//    @PreAuthorize(
//            "hasAnyRole('ADMIN','SUPER_ADMIN','FACULTY')"
//    )
//    public UserResponse createUser(
//            Authentication authentication,
//            @Valid @RequestBody CreateUserRequest request
//    ) {
//
//        return userService.createUser(
//                request,
//                authentication.getName()
//        );
//    }
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    public List<User> getUsers() {
//        return userService.getAllUsers();
//    }
}