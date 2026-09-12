package com.FirstProject.StudentManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentFeeDto {

    private Integer studentId;
    private String studentName;
    private Long studentFee;
}
