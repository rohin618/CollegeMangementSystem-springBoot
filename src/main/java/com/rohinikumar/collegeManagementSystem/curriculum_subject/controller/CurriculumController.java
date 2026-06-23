package com.rohinikumar.collegeManagementSystem.curriculum_subject.controller;

import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.CreateCurriculumRequest;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.CurriculumResponse;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.UpdateCurriculumRequest;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.service.CurriculumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curriculum")
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService curriculumService;



    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<List<CurriculumResponse>> createCurriculum(
            @Valid @RequestBody CreateCurriculumRequest request
    ) {
        return ResponseEntity.ok(
                curriculumService.createCurriculum(request)
        );
    }



    @GetMapping
    public ResponseEntity<Page<CurriculumResponse>> getCurriculumList(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long academicBatchId,
            @RequestParam(required = false) Long semesterId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<CurriculumResponse> response =
                curriculumService.getCurriculumList(
                        departmentId,
                        academicBatchId,
                        semesterId,
                        page,
                        size
                );

        return ResponseEntity.ok(response);
    }


    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<CurriculumResponse> updateCurriculum(
            @Valid @RequestBody UpdateCurriculumRequest request
    ) {
        return ResponseEntity.ok(
                curriculumService.updateCurriculum(request)
        );
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<String> deleteCurriculum(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                curriculumService.deleteCurriculum(id)
        );
    }
}