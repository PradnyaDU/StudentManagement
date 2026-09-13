package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.dto.StudentCourseDetailsDto;
import com.FirstProject.StudentManagement.dto.StudentDto;
import com.FirstProject.StudentManagement.dto.StudentFeeDto;
import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
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

    WeatherAPIResponse getWeatherData(double lat, double lon) throws Exception;
}
