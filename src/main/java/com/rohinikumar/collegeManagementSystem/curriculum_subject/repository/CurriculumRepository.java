package com.rohinikumar.collegeManagementSystem.curriculum_subject.repository;

import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.entity.CurriculumEntity;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.enums.CurriculumStatus;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Long> {

    boolean existsByDepartmentAndAcademicBatchAndSemesterAndSubject(
            DepartmentEntity department,
            AcademicBatchEntity academicBatch,
            SemesterEntity semester,
            SubjectEntity subject
    );

    boolean existsByDepartmentAndAcademicBatchAndSemesterAndSubjectAndStatus(
            DepartmentEntity department,
            AcademicBatchEntity academicBatch,
            SemesterEntity semester,
            SubjectEntity subject,
            CurriculumStatus status
    );

    List<CurriculumEntity> findAllByStatus(
            CurriculumStatus status
    );

    @Query("""
            SELECT c
            FROM CurriculumEntity c
            WHERE c.status = :status
            AND (:departmentId IS NULL OR c.department.id = :departmentId)
            AND (:academicBatchId IS NULL OR c.academicBatch.id = :academicBatchId)
            AND (:semesterId IS NULL OR c.semester.id = :semesterId)
            ORDER BY c.displayOrder ASC
            """)
    Page<CurriculumEntity> findByFilters(
            @Param("departmentId") Long departmentId,
            @Param("academicBatchId") Long academicBatchId,
            @Param("semesterId") Long semesterId,
            @Param("status") CurriculumStatus status,
            Pageable pageable
    );

    List<CurriculumEntity> findByDepartmentAndAcademicBatchAndSemesterAndStatus(
            DepartmentEntity department,
            AcademicBatchEntity academicBatch,
            SemesterEntity semester,
            CurriculumStatus status
    );

    Optional<CurriculumEntity> findByIdAndStatus(
            Long id,
            CurriculumStatus status
    );

    boolean existsByDepartmentAndAcademicBatchAndSemesterAndSubjectAndStatusAndIdNot(
            DepartmentEntity department,
            AcademicBatchEntity academicBatch,
            SemesterEntity semester,
            SubjectEntity subject,
            CurriculumStatus status,
            Long id
    );


}