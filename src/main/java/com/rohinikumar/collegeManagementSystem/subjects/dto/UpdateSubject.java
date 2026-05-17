package com.rohinikumar.collegeManagementSystem.subjects.dto;

import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectStatus;
import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSubject {

    @NotBlank(message = "Subject code must be filled")
    private String code;

    @NotBlank(message = "Subject name must be filled")
    private String name;

    @NotNull(message = "Credits must be provided")
    @Min(value = 1, message = "Credits must be at least 1")
    private Integer credits;

    @NotNull(message = "Subject type must be selected")
    private SubjectType type;

    @NotBlank(message = "Description must be filled")
    private String description;

    @NotNull(message = "Subject status must be selected")
    private SubjectStatus status;
}
