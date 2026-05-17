package com.rohinikumar.collegeManagementSystem.semester.service;

import com.rohinikumar.collegeManagementSystem.common.exception.ResourceAlreadyExistsException;
import com.rohinikumar.collegeManagementSystem.semester.dto.CreateSemesterRequest;
import com.rohinikumar.collegeManagementSystem.semester.dto.SemesterResponse;
import com.rohinikumar.collegeManagementSystem.semester.dto.UpdateSemesterRequest;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.semester.enums.SemesterStatus;
import com.rohinikumar.collegeManagementSystem.semester.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public SemesterResponse createSemester(CreateSemesterRequest request) {

        if (semesterRepository.existsBySemesterNumber(request.getSemesterNumber())) {
            throw new ResourceAlreadyExistsException("Semester already exists");
        }

        SemesterEntity semester = SemesterEntity.builder()
                .semesterNumber(request.getSemesterNumber())
                .name("Semester " + request.getSemesterNumber())
                .status(SemesterStatus.ACTIVE)
                .build();

        SemesterEntity savedSemester = semesterRepository.save(semester);

        return mapToResponse(savedSemester);
    }

    public List<SemesterResponse> getAllSemesterList() {
        List<SemesterEntity> semesters =
                semesterRepository.findAllByStatus(SemesterStatus.ACTIVE);

        return semesters.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SemesterResponse updateSemester(UpdateSemesterRequest request, Long id) {
        SemesterEntity semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        semester.setSemesterNumber(request.getSemesterNumber());
        semester.setName("Semester " + request.getSemesterNumber());
        semester.setStatus(request.getStatus());

        SemesterEntity updated = semesterRepository.save(semester);

        return mapToResponse(updated);
    }

    public String deleteSemester(Long id) {
        SemesterEntity semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        semester.setStatus(SemesterStatus.INACTIVE);
        semesterRepository.save(semester);

        return "Semester deleted successfully";
    }


    private SemesterResponse mapToResponse(SemesterEntity semester) {
        return SemesterResponse.builder()
                .id(semester.getId())
                .name(semester.getName())
                .semesterNumber(semester.getSemesterNumber())
                .status(semester.getStatus())
                .build();
    }
}