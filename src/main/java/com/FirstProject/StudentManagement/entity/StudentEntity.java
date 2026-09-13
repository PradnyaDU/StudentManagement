package com.FirstProject.StudentManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String studentName;
    private String studentEmail;

    @ManyToMany
    @JoinTable(
            name = "student_course_relation",
            schema = "dbo",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<SubjectEntity> courses = new HashSet<>();
}
