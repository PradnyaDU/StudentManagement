package com.FirstProject.StudentManagement.controllers;

import com.FirstProject.StudentManagement.dto.SubjectsDto;
import com.FirstProject.StudentManagement.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping()
    public List<SubjectsDto> getAllStudent() {
        return subjectService.getAllSubjects();
    }
}
