package com.rohinikumar.collegeManagementSystem.department.dto;


import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private DepartmentStatus status;
}
