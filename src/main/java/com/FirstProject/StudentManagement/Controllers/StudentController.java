package com.FirstProject.StudentManagement.Controllers;

import com.FirstProject.StudentManagement.DTO.AssignCourseRequest;
import com.FirstProject.StudentManagement.DTO.StudentCourseDetailsDto;
import com.FirstProject.StudentManagement.DTO.StudentDto;
import com.FirstProject.StudentManagement.DTO.StudentFeeDto;
import com.FirstProject.StudentManagement.Service.OpenWeatherService;
import com.FirstProject.StudentManagement.Service.StudentService;
import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final OpenWeatherService openWeatherService;


    @GetMapping()
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getStudentsById(@PathVariable int id) {
        return studentService.getStudentsById(id);
    }

    @PostMapping("/createStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public boolean deleteStudentsById(@PathVariable int id) {
        return studentService.deleteStudentsById(id);
    }

    @PutMapping("/updateStudent/{id}")
    public StudentDto updateStudentsById(@PathVariable int id, @Valid @RequestBody StudentDto studentDto) {
        return studentService.updateStudentsById(id, studentDto);
    }

    @GetMapping("/{id}/total-fee")
    public StudentFeeDto getStudentTotalFee(@PathVariable int id) {
        return studentService.getStudentTotalFee(id);
    }

    @PostMapping("/{studentId}/assign-course")
    public StudentCourseDetailsDto assignCourseToStudent(
            @PathVariable int studentId,
            @Valid @RequestBody AssignCourseRequest request
    ) {
        return studentService.assignCourseToStudent(
                studentId,
                request.courseId()
        );
    }

    @DeleteMapping("/{studentId}/remove-course")
    public StudentCourseDetailsDto removeCourseToStudent(
            @PathVariable int studentId,
            @Valid @RequestBody AssignCourseRequest request
    ) {
        return studentService.removeCourseToStudent(
                studentId,
                request.courseId()
        );
    }

    @GetMapping("/getWeather")
    public WeatherAPIResponse getWeather(@RequestParam   double lat, @RequestParam double lon) {
        return openWeatherService.getWeatherData(lat, lon);
    }
}
