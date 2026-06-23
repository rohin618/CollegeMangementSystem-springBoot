
        package com.rohinikumar.collegeManagementSystem.studentManagement.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String gender;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long academicBatchId;

    @NotNull
    private Long semesterId;
}

