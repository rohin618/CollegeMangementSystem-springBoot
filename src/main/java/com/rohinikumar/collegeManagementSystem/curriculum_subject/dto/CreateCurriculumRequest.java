package com.rohinikumar.collegeManagementSystem.curriculum_subject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCurriculumRequest {
    private Long departmentId;

    private Long academicBatchId;

    private Long semesterId;

    private List<Long> subjectIds;
}
