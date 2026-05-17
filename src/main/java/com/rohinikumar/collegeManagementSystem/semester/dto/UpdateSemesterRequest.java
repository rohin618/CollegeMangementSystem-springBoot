package com.rohinikumar.collegeManagementSystem.semester.dto;

import com.rohinikumar.collegeManagementSystem.semester.enums.SemesterStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSemesterRequest {

    @NotNull
    @Min(1)
    @Max(8)
    private Integer semesterNumber;

    @NotNull(message = "Status Should not be null")
    private SemesterStatus status;
}