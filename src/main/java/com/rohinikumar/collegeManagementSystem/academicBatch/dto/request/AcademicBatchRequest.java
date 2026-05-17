package com.rohinikumar.collegeManagementSystem.academicBatch.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicBatchRequest {

    @NotBlank(message = "Department Name must be filled")
    private String name;

    @NotNull(message = "Start year must be provided")
    private Integer startYear;

    @NotNull(message = "End year must be provided")
    private Integer endYear;
}
