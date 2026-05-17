package com.rohinikumar.collegeManagementSystem.semester.repository;

import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.semester.enums.SemesterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {

    boolean existsBySemesterNumber(Integer semesterNumber);

    List<SemesterEntity> findAllByStatus(SemesterStatus status);
}