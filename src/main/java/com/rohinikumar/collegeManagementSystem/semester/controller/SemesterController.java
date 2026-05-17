package com.rohinikumar.collegeManagementSystem.semester.controller;

import com.rohinikumar.collegeManagementSystem.semester.dto.CreateSemesterRequest;
import com.rohinikumar.collegeManagementSystem.semester.dto.SemesterResponse;
import com.rohinikumar.collegeManagementSystem.semester.dto.UpdateSemesterRequest;
import com.rohinikumar.collegeManagementSystem.semester.service.SemesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public SemesterResponse createSemester(
            @Valid @RequestBody CreateSemesterRequest request
    ) {
        return semesterService.createSemester(request);
    }

    @GetMapping
    public List<SemesterResponse> getAllSemesters() {
        return semesterService.getAllSemesterList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public SemesterResponse updateSemester(
            @Valid @RequestBody UpdateSemesterRequest request,
            @PathVariable Long id
    ) {
        return semesterService.updateSemester(request, id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteSemester(@PathVariable Long id) {
        return semesterService.deleteSemester(id);
    }
}