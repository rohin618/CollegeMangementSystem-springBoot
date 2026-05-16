package com.rohinikumar.collegeManagementSystem.department.service;


import com.rohinikumar.collegeManagementSystem.common.exception.GlobalExceptionHandler;
import com.rohinikumar.collegeManagementSystem.common.exception.ResourceAlreadyExistsException;
import com.rohinikumar.collegeManagementSystem.department.dto.CreateDepartmentRequest;
import com.rohinikumar.collegeManagementSystem.department.dto.DepartmentResponse;
import com.rohinikumar.collegeManagementSystem.department.dto.UpdateDepartmentRequest;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import com.rohinikumar.collegeManagementSystem.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // For Create Single Department
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {


        if (departmentRepository.existsByCode(request.getCode())) {
            throw new ResourceAlreadyExistsException("Department code already exists");
        }

        if (departmentRepository.existsByName(request.getName())) {
            throw new ResourceAlreadyExistsException("Department name already exists");
        }
        DepartmentEntity departmentEntity = buildDepartment(request);

        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);

        return mapToResponse(savedDepartment);
    }

    // Get All Department


    public List<DepartmentResponse> getAllDepartmentList() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAllByStatus(DepartmentStatus.ACTIVE);

        return departmentEntityList.stream().map(this::mapToResponse).toList();
    }


    // update the department


    public DepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest, Long id) {
        DepartmentEntity existDepartment = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        existDepartment.setName(updateDepartmentRequest.getName());
        existDepartment.setCode(updateDepartmentRequest.getCode());
        existDepartment.setDescription(updateDepartmentRequest.getDescription());
        existDepartment.setStatus(updateDepartmentRequest.getStatus());

        DepartmentEntity existDepartmentResponse = departmentRepository.save(existDepartment);

        return mapToResponse(existDepartmentResponse);
    }


    // delete the department

    public String deleteDepartment(Long id) {
        DepartmentEntity existDepartment = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        existDepartment.setStatus(DepartmentStatus.INACTIVE);

        departmentRepository.save(existDepartment);

        return "Department deleted successfully";
    }

    // Get Single Department
    public DepartmentResponse getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Department Not Found")
        );

        return mapToResponse(departmentEntity);
    }


    private DepartmentEntity buildDepartment(CreateDepartmentRequest request) {

        return DepartmentEntity.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .status(DepartmentStatus.ACTIVE)
                .build();
    }

    private DepartmentResponse mapToResponse(DepartmentEntity departmentEntity) {

        return DepartmentResponse.builder().id(departmentEntity.getId())
                .name(departmentEntity.getName())
                .code(departmentEntity.getCode())
                .description(departmentEntity.getDescription())
                .status(departmentEntity.getStatus())
                .build();
    }
}
