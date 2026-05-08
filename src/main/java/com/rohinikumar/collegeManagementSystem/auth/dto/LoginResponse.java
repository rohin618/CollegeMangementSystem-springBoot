package com.rohinikumar.collegeManagementSystem.auth.dto;

import com.rohinikumar.collegeManagementSystem.user.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private String username;

    private Role role;

    private Boolean passwordChanged;
}