package com.rohinikumar.collegeManagementSystem.semester.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSemesterRequest {

    @NotNull(message = "Semester number is required")
    @Min(value = 1, message = "Semester number must be at least 1")
    @Max(value = 8, message = "Semester number must not exceed 8")
    private Integer semesterNumber;
}