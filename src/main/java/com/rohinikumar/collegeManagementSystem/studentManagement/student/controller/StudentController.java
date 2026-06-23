
        package com.rohinikumar.collegeManagementSystem.studentManagement.student.controller;

import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.CreateStudentRequest;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.StudentResponse;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.UpdateStudentRequest;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody CreateStudentRequest request
    ) {
        return ResponseEntity.ok(
                studentService.createStudent(request)
        );
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long academicBatchId,
            @RequestParam(required = false) Long semesterId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                studentService.getStudents(
                        search,
                        departmentId,
                        academicBatchId,
                        semesterId,
                        page,
                        size
                )
        );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<StudentResponse> updateStudent(
            @Valid @RequestBody UpdateStudentRequest request
    ) {
        return ResponseEntity.ok(
                studentService.updateStudent(request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                studentService.deleteStudent(id)
        );
    }
}

