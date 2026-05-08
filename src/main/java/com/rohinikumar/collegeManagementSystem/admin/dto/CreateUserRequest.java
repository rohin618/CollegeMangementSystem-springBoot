package com.rohinikumar.collegeManagementSystem.admin.dto;

import com.rohinikumar.collegeManagementSystem.user.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotNull
    private Role role;
}