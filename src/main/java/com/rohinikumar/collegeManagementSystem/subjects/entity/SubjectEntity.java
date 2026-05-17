package com.rohinikumar.collegeManagementSystem.subjects.entity;


import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectStatus;
import com.rohinikumar.collegeManagementSystem.subjects.enums.SubjectType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer credits;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectType type;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectStatus status;

}
