package com.rohinikumar.collegeManagementSystem.department.dto;


import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDepartmentRequest {
    @NotBlank(message = "Department Name must be filled")
    private String name;

    @NotBlank(message = "Description must be filled")
    private String description;

    @NotBlank(message = "Code Must be Filled")
    private String code;

    private DepartmentStatus status;
}
