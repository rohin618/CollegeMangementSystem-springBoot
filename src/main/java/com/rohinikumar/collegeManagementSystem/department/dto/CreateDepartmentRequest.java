package com.rohinikumar.collegeManagementSystem.department.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDepartmentRequest {

    @NotBlank(message = "Department Name must be filled")
    private String name;

    @NotBlank(message = "Description must be filled")
    private String description;

    @NotBlank(message = "Code Must be Filled")
    private String code;

}
