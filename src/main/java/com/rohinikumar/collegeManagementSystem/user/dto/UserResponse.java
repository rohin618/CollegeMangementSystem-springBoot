package com.rohinikumar.collegeManagementSystem.user.dto;

import com.rohinikumar.collegeManagementSystem.user.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Boolean isActive;
}