package com.rohinikumar.collegeManagementSystem.academicBatch.dto.request;


import com.rohinikumar.collegeManagementSystem.academicBatch.enums.AcademicYearStatus;
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
public class UpdateAcademicBatch {

    private Long id;
    @NotBlank(message = "Academic batch name must be filled")
    private String name;

    @NotNull(message = "Start year must be provided")
    private Integer startYear;

    @NotNull(message = "End year must be provided")
    private Integer endYear;

    @NotNull(message = "Status must be selected")
    private AcademicYearStatus status;
}
