package com.rohinikumar.collegeManagementSystem.curriculum_subject.entity;


import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.enums.CurriculumStatus;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "curriculum_subject")
public class CurriculumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_batch_id", nullable = false)
    private AcademicBatchEntity academicBatch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id", nullable = false)
    private SemesterEntity semester;

    private Integer displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurriculumStatus status;
}
