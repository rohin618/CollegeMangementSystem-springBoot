package com.rohinikumar.collegeManagementSystem.academicBatch.service;


import com.rohinikumar.collegeManagementSystem.academicBatch.dto.request.AcademicBatchRequest;
import com.rohinikumar.collegeManagementSystem.academicBatch.dto.request.UpdateAcademicBatch;
import com.rohinikumar.collegeManagementSystem.academicBatch.dto.response.AcademicBatchResponse;
import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.academicBatch.enums.AcademicYearStatus;
import com.rohinikumar.collegeManagementSystem.academicBatch.repository.AcademicBatchRepository;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicBatchService {

    private final AcademicBatchRepository academicBatchRepository;

    // Create Academic Batch

    public AcademicBatchResponse createAcademicBatch(AcademicBatchRequest academicBatchRequest){
        AcademicBatchEntity academicBatchEntity = buildAcademicBatch(academicBatchRequest);

        AcademicBatchEntity academicBatchEntityRes = academicBatchRepository.save(academicBatchEntity);

        return mapToResponse(academicBatchEntityRes);
    }


    public List<AcademicBatchResponse> getAllAcademicBatch(){

        List<AcademicBatchEntity> academicBatchList = academicBatchRepository.findAllByStatus(AcademicYearStatus.ACTIVE);
        return academicBatchList.stream().map(this::mapToResponse).toList();
    }


    public AcademicBatchResponse updateAcademicBatch(UpdateAcademicBatch updateAcademicBatch,Long id){
        AcademicBatchEntity academicBatchEntity = academicBatchRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Acadimic Year not Found"));

        academicBatchEntity.setName(updateAcademicBatch.getName());
        academicBatchEntity.setStartYear(updateAcademicBatch.getStartYear());
        academicBatchEntity.setEndYear(updateAcademicBatch.getEndYear());
        academicBatchEntity.setStatus(updateAcademicBatch.getStatus());

        return mapToResponse(academicBatchRepository.save(academicBatchEntity));
    }

    public String deleteAcademicBatch(Long id){
        AcademicBatchEntity academicBatchEntity = academicBatchRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Acadimic Year not Found"));

        academicBatchEntity.setStatus(AcademicYearStatus.INACTIVE);
        academicBatchRepository.save(academicBatchEntity);

        return "Deleted Successfully";
    }



    private AcademicBatchEntity buildAcademicBatch(AcademicBatchRequest academicBatchRequest){
        return AcademicBatchEntity.builder().name(academicBatchRequest.getName())
                .startYear(academicBatchRequest.getStartYear())
                .endYear(academicBatchRequest.getEndYear())
                .status(AcademicYearStatus.ACTIVE).build();
    }

    private AcademicBatchResponse mapToResponse(AcademicBatchEntity academicBatchEntity){
        return AcademicBatchResponse.builder()
                .id(academicBatchEntity.getId())
                .endYear(academicBatchEntity.getEndYear())
                .startYear(academicBatchEntity.getStartYear())
                .status(academicBatchEntity.getStatus())
                .name(academicBatchEntity.getName())
                .build();
    }
    
}
