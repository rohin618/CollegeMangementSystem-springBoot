package com.rohinikumar.collegeManagementSystem.semester.entity;

import com.rohinikumar.collegeManagementSystem.semester.enums.SemesterStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semester")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemesterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private Integer semesterNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SemesterStatus status;
}