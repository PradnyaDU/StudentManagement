package com.FirstProject.StudentManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersDto {

    Integer id;
    @NotBlank(message = "User Name is mandatory")
    String userName;

    @NotBlank(message = "User Password is required")
    String userPassword;

    String role;

    List<String> authorities; // New field for authoritiess
}
