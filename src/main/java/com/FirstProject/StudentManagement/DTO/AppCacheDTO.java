package com.FirstProject.StudentManagement.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "configurations")
@Getter
@Setter
public class AppCacheDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String configKey;

    private String configValue;
}

