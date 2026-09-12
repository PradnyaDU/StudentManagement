package com.FirstProject.StudentManagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {

    Integer id;
    @NotBlank(message = "Student Name is mandatory")
    String studentName;

    @NotBlank(message = "Student email is required")
    @Email(message = "Enter a valid email address")
    String studentEmail;
}
