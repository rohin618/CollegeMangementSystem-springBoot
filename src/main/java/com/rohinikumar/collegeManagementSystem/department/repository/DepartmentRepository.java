package com.rohinikumar.collegeManagementSystem.department.repository;

import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

    boolean existsByCode(String code);
    boolean existsByName(String code);

    List<DepartmentEntity> findAllByStatus(DepartmentStatus status);


//    Optional<DepartmentEntity> findById(Long id);

}
