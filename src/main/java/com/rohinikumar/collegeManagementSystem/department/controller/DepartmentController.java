package com.rohinikumar.collegeManagementSystem.department.controller;


import com.rohinikumar.collegeManagementSystem.department.dto.CreateDepartmentRequest;
import com.rohinikumar.collegeManagementSystem.department.dto.DepartmentResponse;
import com.rohinikumar.collegeManagementSystem.department.dto.UpdateDepartmentRequest;
import com.rohinikumar.collegeManagementSystem.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public DepartmentResponse createDepartment(@Valid @RequestBody
                                               CreateDepartmentRequest createDepartmentRequest) {
        return departmentService.createDepartment(createDepartmentRequest);
    }

    @GetMapping
    public List<DepartmentResponse> getAllDepartment() {
        return departmentService.getAllDepartmentList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@Valid @RequestBody UpdateDepartmentRequest updateDepartmentRequest, @PathVariable Long id) {
        return departmentService.updateDepartment(updateDepartmentRequest, id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}
