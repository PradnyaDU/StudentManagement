package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.DTO.SubjectsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    List<SubjectsDto> getAllSubjects();
}
