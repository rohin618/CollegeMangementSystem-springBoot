package com.rohinikumar.collegeManagementSystem.department.entity;


import com.rohinikumar.collegeManagementSystem.department.enums.DepartmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true)
    private String name;


    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false,unique = false)
    private String description ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartmentStatus status;
}
