package com.rohinikumar.collegeManagementSystem.semester.dto;

import com.rohinikumar.collegeManagementSystem.semester.enums.SemesterStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SemesterResponse {

    private Long id;
    private String name;
    private Integer semesterNumber;
    private SemesterStatus status;
}