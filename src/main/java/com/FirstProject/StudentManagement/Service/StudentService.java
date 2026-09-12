package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.DTO.StudentCourseDetailsDto;
import com.FirstProject.StudentManagement.DTO.StudentDto;
import com.FirstProject.StudentManagement.DTO.StudentFeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentsById(int id);

    StudentDto createStudent(StudentDto studentDto);

    boolean deleteStudentsById(int id);

    StudentDto updateStudentsById(int id, StudentDto studentDto);

    StudentFeeDto getStudentTotalFee(int id);

    StudentCourseDetailsDto assignCourseToStudent(int studentId, int courseId);

    StudentCourseDetailsDto removeCourseToStudent(int studentId, int courseId);
}
