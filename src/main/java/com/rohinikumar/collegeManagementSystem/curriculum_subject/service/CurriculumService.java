package com.rohinikumar.collegeManagementSystem.curriculum_subject.service;

import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.academicBatch.repository.AcademicBatchRepository;
import com.rohinikumar.collegeManagementSystem.common.exception.ResourceAlreadyExistsException;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.CreateCurriculumRequest;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.CurriculumResponse;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.dto.UpdateCurriculumRequest;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.entity.CurriculumEntity;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.enums.CurriculumStatus;
import com.rohinikumar.collegeManagementSystem.curriculum_subject.repository.CurriculumRepository;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.department.repository.DepartmentRepository;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.semester.repository.SemesterRepository;
import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import com.rohinikumar.collegeManagementSystem.subjects.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final DepartmentRepository departmentRepository;
    private final AcademicBatchRepository academicBatchRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;

// CurriculumService.java

    public List<CurriculumResponse> createCurriculum(
            CreateCurriculumRequest request
    ) {

        DepartmentEntity department = departmentRepository.findById(
                request.getDepartmentId()
        ).orElseThrow(() -> new RuntimeException("Department not found"));

        AcademicBatchEntity academicBatch = academicBatchRepository.findById(
                request.getAcademicBatchId()
        ).orElseThrow(() -> new RuntimeException("Academic Batch not found"));

        SemesterEntity semester = semesterRepository.findById(
                request.getSemesterId()
        ).orElseThrow(() -> new RuntimeException("Semester not found"));

        List<CurriculumEntity> curriculumList = new ArrayList<>();

        int displayOrder = 1;

        for (Long subjectId : request.getSubjectIds()) {

            SubjectEntity subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            boolean alreadyExists =
                    curriculumRepository
                            .existsByDepartmentAndAcademicBatchAndSemesterAndSubjectAndStatus(
                                    department,
                                    academicBatch,
                                    semester,
                                    subject,
                                    CurriculumStatus.ACTIVE
                            );

            if (alreadyExists) {
                throw new ResourceAlreadyExistsException(
                        subject.getName() + " already mapped"
                );
            }

            CurriculumEntity curriculum = CurriculumEntity.builder()
                    .department(department)
                    .academicBatch(academicBatch)
                    .semester(semester)
                    .subject(subject)
                    .displayOrder(displayOrder++)
                    .status(CurriculumStatus.ACTIVE)
                    .build();

            curriculumList.add(curriculum);
        }

        List<CurriculumEntity> savedCurriculums =
                curriculumRepository.saveAll(curriculumList);

        return savedCurriculums.stream()
                .map(this::mapToResponse)
                .toList();
    }



    public Page<CurriculumResponse> getCurriculumList(
            Long departmentId,
            Long academicBatchId,
            Long semesterId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<CurriculumEntity> curriculumPage =
                curriculumRepository.findByFilters(
                        departmentId,
                        academicBatchId,
                        semesterId,
                        CurriculumStatus.ACTIVE,
                        pageable
                );

        return curriculumPage.map(this::mapToResponse);
    }


    public CurriculumResponse updateCurriculum(
            UpdateCurriculumRequest request
    ) {

        CurriculumEntity curriculum =
                curriculumRepository.findById(request.getId())
                        .orElseThrow(() ->
                                new RuntimeException("Curriculum not found"));

        DepartmentEntity department =
                departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        AcademicBatchEntity academicBatch =
                academicBatchRepository.findById(request.getAcademicBatchId())
                        .orElseThrow(() ->
                                new RuntimeException("Academic Batch not found"));

        SemesterEntity semester =
                semesterRepository.findById(request.getSemesterId())
                        .orElseThrow(() ->
                                new RuntimeException("Semester not found"));

        SubjectEntity subject =
                subjectRepository.findById(request.getSubjectId())
                        .orElseThrow(() ->
                                new RuntimeException("Subject not found"));

        boolean alreadyExists =
                curriculumRepository
                        .existsByDepartmentAndAcademicBatchAndSemesterAndSubjectAndStatusAndIdNot(
                                department,
                                academicBatch,
                                semester,
                                subject,
                                CurriculumStatus.ACTIVE,
                                request.getId()
                        );

        if (alreadyExists) {
            throw new ResourceAlreadyExistsException(
                    "Curriculum mapping already exists"
            );
        }

        curriculum.setDepartment(department);
        curriculum.setAcademicBatch(academicBatch);
        curriculum.setSemester(semester);
        curriculum.setSubject(subject);

        CurriculumEntity updated =
                curriculumRepository.save(curriculum);

        return mapToResponse(updated);
    }



    public String deleteCurriculum(Long id) {

        CurriculumEntity curriculum =
                curriculumRepository
                        .findByIdAndStatus(
                                id,
                                CurriculumStatus.ACTIVE
                        )
                        .orElseThrow(() ->
                                new RuntimeException("Curriculum not found"));

        curriculum.setStatus(CurriculumStatus.INACTIVE);

        curriculumRepository.save(curriculum);

        return "Curriculum deleted successfully";
    }

    private CurriculumResponse mapToResponse(
            CurriculumEntity curriculum
    ) {

        return CurriculumResponse.builder()
                .id(curriculum.getId())

                .departmentId(
                        curriculum.getDepartment().getId()
                )
                .departmentName(
                        curriculum.getDepartment().getName()
                )

                .academicBatchId(
                        curriculum.getAcademicBatch().getId()
                )
                .academicBatchName(
                        curriculum.getAcademicBatch().getName()
                )

                .semesterId(
                        curriculum.getSemester().getId()
                )
                .semesterName(
                        curriculum.getSemester().getName()
                )

                .subjectId(
                        curriculum.getSubject().getId()
                )
                .subjectName(
                        curriculum.getSubject().getName()
                )

                .displayOrder(
                        curriculum.getDisplayOrder()
                )

                .status(
                        curriculum.getStatus()
                )
                .build();
    }
}