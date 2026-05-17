package com.rohinikumar.collegeManagementSystem.subjects.dto;


import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectStatus;
import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponse {
    private Long id;

    private String code;

    private String name;

    private Integer credits;

    private SubjectType type;

    private String description;

    private SubjectStatus status;


}
