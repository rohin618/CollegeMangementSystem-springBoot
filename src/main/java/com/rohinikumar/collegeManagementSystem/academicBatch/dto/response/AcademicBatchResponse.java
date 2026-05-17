package com.rohinikumar.collegeManagementSystem.academicBatch.dto.response;


import com.rohinikumar.collegeManagementSystem.academicBatch.enums.AcademicYearStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicBatchResponse {

    private Long id;
    private String name;
    private Integer startYear;
    private Integer endYear;
    private AcademicYearStatus status;
}
