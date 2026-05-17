package com.rohinikumar.collegeManagementSystem.subjects.service;


import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectPageResponse;
import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectRequest;
import com.rohinikumar.collegeManagementSystem.subjects.dto.SubjectResponse;
import com.rohinikumar.collegeManagementSystem.subjects.dto.UpdateSubject;
import com.rohinikumar.collegeManagementSystem.subjects.entity.SubjectEntity;
import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectStatus;
import com.rohinikumar.collegeManagementSystem.subjects.repository.SubjectRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectResponse createSubject(SubjectRequest subjectRequest){

        SubjectEntity subjectEntity = buildToEntity(subjectRequest);
        return mapToResponse(subjectRepository.save(subjectEntity));

    }

    public SubjectPageResponse getAllSubjects(
            int page,
            int size,
            String search
    ){

        Pageable pageable = PageRequest.of(page,size);

        Page<SubjectEntity> subjectPage;

        if (search != null && !search.isBlank()) {
            subjectPage =
                    subjectRepository
                            .findByStatusAndNameContainingIgnoreCaseOrStatusAndCodeContainingIgnoreCase(
                                    SubjectStatus.ACTIVE,
                                    search,
                                    SubjectStatus.ACTIVE,
                                    search,
                                    pageable
                            );
        } else {
            subjectPage =
                    subjectRepository.findByStatus(SubjectStatus.ACTIVE,pageable);
        }

        List<SubjectResponse> subjects =
                subjectPage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();


        return SubjectPageResponse.builder()
                .content(subjects)
                .pageNumber(subjectPage.getNumber())
                .pageSize(subjectPage.getSize())
                .totalElements(subjectPage.getTotalElements())
                .totalPages(subjectPage.getTotalPages())
                .last(subjectPage.isLast())
                .build();

    }


    @Transactional
    public SubjectResponse updateSubject(Long id, UpdateSubject updateSubject){
        SubjectEntity subjectEntity = subjectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Subject Not Found"));

        subjectEntity.setCode(updateSubject.getCode());
        subjectEntity.setName(updateSubject.getName());
        subjectEntity.setCredits(updateSubject.getCredits());
        subjectEntity.setType(updateSubject.getType());
        subjectEntity.setDescription(updateSubject.getDescription());
        subjectEntity.setStatus(updateSubject.getStatus());

        SubjectEntity updatedSubject = subjectRepository.save(subjectEntity);

        return mapToResponse(updatedSubject);
    }

    public String deleteSubject(Long id){
        SubjectEntity subjectEntity = subjectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Subject Not Found"));

        subjectEntity.setStatus(SubjectStatus.INACTIVE);

        SubjectEntity updatedSubject = subjectRepository.save(subjectEntity);

        return "Deleted Successfully";
    }




    private SubjectResponse mapToResponse(SubjectEntity subjectEntity) {
        return SubjectResponse.builder()
                .id(subjectEntity.getId())
                .code(subjectEntity.getCode())
                .name(subjectEntity.getName())
                .credits(subjectEntity.getCredits())
                .type(subjectEntity.getType())
                .description(subjectEntity.getDescription())
                .status(subjectEntity.getStatus())
                .build();
    }

    private SubjectEntity buildToEntity(SubjectRequest subjectRequest){
        return SubjectEntity.builder()
                .name(subjectRequest.getName())
                .code(subjectRequest.getCode())
                .description(subjectRequest.getDescription())
                .type(subjectRequest.getType())
                .credits(subjectRequest.getCredits())
                .status(SubjectStatus.ACTIVE).build();
    }
}
