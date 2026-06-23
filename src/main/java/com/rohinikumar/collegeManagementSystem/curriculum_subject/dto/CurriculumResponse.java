package com.rohinikumar.collegeManagementSystem.curriculum_subject.dto;

import com.rohinikumar.collegeManagementSystem.curriculum_subject.enums.CurriculumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumResponse {

    private Long id;

    private Long departmentId;
    private String departmentName;

    private Long academicBatchId;
    private String academicBatchName;

    private Long semesterId;
    private String semesterName;

    private Long subjectId;
    private String subjectName;

    private Integer displayOrder;

    private CurriculumStatus status;
}