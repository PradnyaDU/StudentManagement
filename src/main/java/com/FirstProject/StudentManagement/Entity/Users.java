package com.FirstProject.StudentManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String userName;
    private String userPassword;
    private String role;
    private String authorities; // New field for authorities
}
