package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.dto.SubjectsDto;
import com.FirstProject.StudentManagement.entity.SubjectEntity;
import com.FirstProject.StudentManagement.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImplementation implements SubjectService {
    private final SubjectRepo subjectRepo;

    @Override
    public List<SubjectsDto> getAllSubjects() {
        List<SubjectEntity> subjectEntity = subjectRepo.findAll();
        List<SubjectsDto> subjectDtosList = subjectEntity.stream().map(subjectEntity1 -> new SubjectsDto(subjectEntity1.getCourseId(), subjectEntity1.getCourseName(), subjectEntity1.getCourseFee())).toList();
        return subjectDtosList;
    }
}
