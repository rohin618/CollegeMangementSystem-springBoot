package com.rohinikumar.collegeManagementSystem.subjects.controller;

import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectPageResponse;
import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectRequest;
import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectResponse;
import com.rohinikumar.collegeManagementSystem.subjects.dto.UpdateSubject;
import com.rohinikumar.collegeManagementSystem.subjects.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public SubjectResponse createSubject(
            @Valid @RequestBody SubjectRequest subjectRequest
    ) {
        return subjectService.createSubject(subjectRequest);
    }

    @GetMapping("/pagination")
    public SubjectPageResponse getAllSubjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return subjectService.getAllSubjects(
                page,
                size,
                search
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSubject updateSubject
    ) {
        return subjectService.updateSubject(
                id,
                updateSubject
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteSubject(
            @PathVariable Long id
    ) {
        return subjectService.deleteSubject(id);
    }
}