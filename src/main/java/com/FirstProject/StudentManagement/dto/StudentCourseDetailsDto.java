package com.FirstProject.StudentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class StudentCourseDetailsDto {
    private int studentId;
    private String studentName;
    private List<Integer> courseIds;
    private List<String> courseNames;
    private Long totalFee;
}