package com.rohinikumar.collegeManagementSystem.subjects.repository;

import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectStatus;
import com.rohinikumar.collegeManagementSystem.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
    Page<SubjectEntity> findByStatusAndNameContainingIgnoreCaseOrStatusAndCodeContainingIgnoreCase(
            SubjectStatus status1,
            String name,
            SubjectStatus status2,
            String code,
            Pageable pageable
    );
    Page<SubjectEntity> findByStatus(
            SubjectStatus status,
            Pageable pageable
    );
}
