package com.rohinikumar.collegeManagementSystem.academicBatch.controller;


import com.rohinikumar.collegeManagementSystem.academicBatch.dto.request.AcademicBatchRequest;
import com.rohinikumar.collegeManagementSystem.academicBatch.dto.request.UpdateAcademicBatch;
import com.rohinikumar.collegeManagementSystem.academicBatch.dto.response.AcademicBatchResponse;
import com.rohinikumar.collegeManagementSystem.academicBatch.service.AcademicBatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/academicBatch")
@RequiredArgsConstructor
public class AcademicBatchController {

    private final AcademicBatchService academicBatchService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public AcademicBatchResponse createAcademicBatch(@RequestBody @Valid AcademicBatchRequest academicBatchRequest){
        return academicBatchService.createAcademicBatch(academicBatchRequest);
    }

    @GetMapping
    public List<AcademicBatchResponse> getAllAcademicBatch(){
        return academicBatchService.getAllAcademicBatch();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public AcademicBatchResponse updateAcademicBatch(@PathVariable Long id,@RequestBody UpdateAcademicBatch updateAcademicBatch){
        return academicBatchService.updateAcademicBatch(updateAcademicBatch,id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteAcademicBatch(@PathVariable Long id ){
        return academicBatchService.deleteAcademicBatch(id);
    }
}
