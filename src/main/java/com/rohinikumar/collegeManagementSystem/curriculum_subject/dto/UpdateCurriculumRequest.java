
package com.rohinikumar.collegeManagementSystem.curriculum_subject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCurriculumRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long academicBatchId;

    @NotNull
    private Long semesterId;

    @NotNull
    private Long subjectId;
}

