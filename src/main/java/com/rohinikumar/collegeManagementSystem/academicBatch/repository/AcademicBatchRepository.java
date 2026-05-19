package com.rohinikumar.collegeManagementSystem.academicBatch.repository;

import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.academicBatch.enums.AcademicYearStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicBatchRepository extends JpaRepository<AcademicBatchEntity,Long> {
    List<AcademicBatchEntity> findAllByStatus(AcademicYearStatus status);
}
