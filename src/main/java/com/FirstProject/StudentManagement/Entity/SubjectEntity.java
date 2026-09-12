package com.FirstProject.StudentManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "courses", schema = "dbo")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;
    private Long courseFee;
}
