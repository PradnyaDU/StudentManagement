package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.dto.SubjectsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    List<SubjectsDto> getAllSubjects();
}
