package com.rohinikumar.collegeManagementSystem.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkCreateUserRequest {

    @Valid
    @NotEmpty(message = "User list cannot be empty")
    private List<CreateUserRequest> users;
}