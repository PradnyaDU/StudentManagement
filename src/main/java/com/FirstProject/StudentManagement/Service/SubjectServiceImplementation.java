package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.DTO.SubjectsDto;
import com.FirstProject.StudentManagement.Entity.SubjectEntity;
import com.FirstProject.StudentManagement.Repository.SubjectRepo;
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
