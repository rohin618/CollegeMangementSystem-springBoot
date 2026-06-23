package com.rohinikumar.collegeManagementSystem.studentManagement.student.repository;

import com.rohinikumar.collegeManagementSystem.studentManagement.student.entity.StudentEntity;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.enums.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository  extends JpaRepository<StudentEntity,Long> {

    boolean existsByRegisterNumber(String registerNumber);

    boolean existsByEmail(String email);

    @Query("""
    SELECT s
    FROM StudentEntity s
    WHERE s.status = :status
    AND (
        :search IS NULL
        OR LOWER(s.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
    )
    AND (
        :departmentId IS NULL
        OR s.department.id = :departmentId
    )
    AND (
        :academicBatchId IS NULL
        OR s.academicBatch.id = :academicBatchId
    )
    AND (
        :semesterId IS NULL
        OR s.semester.id = :semesterId
    )
""")
    Page<StudentEntity> findStudentsWithFilters(
            @Param("search") String search,
            @Param("departmentId") Long departmentId,
            @Param("academicBatchId") Long academicBatchId,
            @Param("semesterId") Long semesterId,
            @Param("status") StudentStatus status,
            Pageable pageable
    );
}
