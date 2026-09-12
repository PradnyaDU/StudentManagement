package com.FirstProject.StudentManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectsDto {

    Integer id;
    @NotBlank(message = "subject Name is mandatory")
    String subjectName;
    Long subjectFees;
}
