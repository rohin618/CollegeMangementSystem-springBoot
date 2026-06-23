package com.rohinikumar.collegeManagementSystem.studentManagement.student.service;

import com.rohinikumar.collegeManagementSystem.academicBatch.entity.AcademicBatchEntity;
import com.rohinikumar.collegeManagementSystem.academicBatch.repository.AcademicBatchRepository;
import com.rohinikumar.collegeManagementSystem.common.exception.ResourceAlreadyExistsException;
import com.rohinikumar.collegeManagementSystem.department.entity.DepartmentEntity;
import com.rohinikumar.collegeManagementSystem.department.repository.DepartmentRepository;
import com.rohinikumar.collegeManagementSystem.semester.entity.SemesterEntity;
import com.rohinikumar.collegeManagementSystem.semester.repository.SemesterRepository;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.CreateStudentRequest;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.StudentResponse;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.dto.UpdateStudentRequest;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.entity.StudentEntity;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.enums.StudentStatus;
import com.rohinikumar.collegeManagementSystem.studentManagement.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final AcademicBatchRepository academicBatchRepository;
    private final SemesterRepository semesterRepository;

    public StudentResponse createStudent(
            CreateStudentRequest request
    ) {

        if (studentRepository.existsByRegisterNumber(
                request.getRegisterNumber()
        )) {
            throw new ResourceAlreadyExistsException(
                    "Register number already exists"
            );
        }

        if (studentRepository.existsByEmail(
                request.getEmail()
        )) {
            throw new ResourceAlreadyExistsException(
                    "Email already exists"
            );
        }

        DepartmentEntity department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException("Department not found"));

        AcademicBatchEntity academicBatch =
                academicBatchRepository.findById(
                        request.getAcademicBatchId()
                ).orElseThrow(() ->
                        new RuntimeException("Academic Batch not found"));

        SemesterEntity semester =
                semesterRepository.findById(
                        request.getSemesterId()
                ).orElseThrow(() ->
                        new RuntimeException("Semester not found"));

        StudentEntity student = StudentEntity.builder()
                .registerNumber(request.getRegisterNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .department(department)
                .academicBatch(academicBatch)
                .semester(semester)
                .status(StudentStatus.ACTIVE)
                .build();

        StudentEntity savedStudent =
                studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    public Page<StudentResponse> getStudents(
            String search,
            Long departmentId,
            Long academicBatchId,
            Long semesterId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository
                .findStudentsWithFilters(
                        search,
                        departmentId,
                        academicBatchId,
                        semesterId,
                        StudentStatus.ACTIVE,
                        pageable
                )
                .map(this::mapToResponse);
    }

    public StudentResponse updateStudent(
            UpdateStudentRequest request
    ) {

        StudentEntity student =
                studentRepository.findById(request.getId())
                        .orElseThrow(() ->
                                new RuntimeException("Student not found"));

        DepartmentEntity department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException("Department not found"));

        AcademicBatchEntity academicBatch =
                academicBatchRepository.findById(
                        request.getAcademicBatchId()
                ).orElseThrow(() ->
                        new RuntimeException("Academic Batch not found"));

        SemesterEntity semester =
                semesterRepository.findById(
                        request.getSemesterId()
                ).orElseThrow(() ->
                        new RuntimeException("Semester not found"));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setDepartment(department);
        student.setAcademicBatch(academicBatch);
        student.setSemester(semester);

        StudentEntity updatedStudent =
                studentRepository.save(student);

        return mapToResponse(updatedStudent);
    }

    public String deleteStudent(Long id) {

        StudentEntity student =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Student not found"));

        student.setStatus(StudentStatus.INACTIVE);

        studentRepository.save(student);

        return "Student deleted successfully";
    }

    private StudentResponse mapToResponse(
            StudentEntity student
    ) {

        return StudentResponse.builder()
                .id(student.getId())
                .registerNumber(student.getRegisterNumber())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())

                .departmentId(
                        student.getDepartment().getId()
                )
                .departmentName(
                        student.getDepartment().getName()
                )

                .academicBatchId(
                        student.getAcademicBatch().getId()
                )
                .academicBatchName(
                        student.getAcademicBatch().getName()
                )

                .semesterId(
                        student.getSemester().getId()
                )
                .semesterName(
                        student.getSemester().getName()
                )

                .status(student.getStatus())
                .build();
    }
}

