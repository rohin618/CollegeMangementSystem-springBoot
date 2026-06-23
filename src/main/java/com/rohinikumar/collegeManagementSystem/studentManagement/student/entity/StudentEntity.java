package com.rohinikumar.collegeManagementSystem.studentManagement.student.entity;


import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.enums.StudentStatus;
import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registerNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_batch_id", nullable = false)
    private AcademicBatchEntity academicBatch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id", nullable = false)
    private SemesterEntity semester;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
