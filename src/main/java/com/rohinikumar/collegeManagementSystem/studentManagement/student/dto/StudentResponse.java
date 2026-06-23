package com.rohinikumar.collegeManagementSystem.studentManagement.student.dto;

import com.rohinikumar.collegeManagementSystem.studentManagement.student.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;

    private String registerNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String gender;

    private Long departmentId;
    private String departmentName;

    private Long academicBatchId;
    private String academicBatchName;

    private Long semesterId;
    private String semesterName;

    private StudentStatus status;
}
