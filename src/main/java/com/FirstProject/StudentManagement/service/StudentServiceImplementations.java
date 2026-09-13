package com.FirstProject.StudentManagement.service;

import com.FirstProject.StudentManagement.apiresponse.WeatherAPIResponse;
import com.FirstProject.StudentManagement.dto.StudentCourseDetailsDto;
import com.FirstProject.StudentManagement.dto.StudentDto;
import com.FirstProject.StudentManagement.dto.StudentFeeDto;
import com.FirstProject.StudentManagement.entity.StudentEntity;
import com.FirstProject.StudentManagement.entity.SubjectEntity;
import com.FirstProject.StudentManagement.repository.StudentRepository;
import com.FirstProject.StudentManagement.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementations implements StudentService {

    private final StudentRepository studentRepository;
    private final KafkaProducerService kafkaProducerService;

    private final SubjectRepo subjectRepo;

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentDtosList = studentEntities.stream().map(studentEntity -> new StudentDto(studentEntity.getId(), studentEntity.getStudentName(), studentEntity.getStudentEmail())).toList();

        return studentDtosList;
    }

    @Override
    public StudentDto getStudentsById(int id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student does not exists with id " + id));
        return new StudentDto(studentEntity.getId(), studentEntity.getStudentName(), studentEntity.getStudentEmail());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentName(studentDto.getStudentName());
        studentEntity.setStudentEmail(studentDto.getStudentEmail());

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        kafkaProducerService.sendStudentCreatedEvent(
                savedStudent.getStudentEmail()
        );
        return new StudentDto(
                savedStudent.getId(),
                savedStudent.getStudentName(),
                savedStudent.getStudentEmail()
        );
    }

    @Override
    public boolean deleteStudentsById(int id) {
        StudentEntity studentEntityToDelete = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student does not exists with id " + id));
        studentRepository.delete(studentEntityToDelete);
        return true;
    }

    @Override
    public StudentDto updateStudentsById(int id, StudentDto studentDto) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student does not exists with id " + id));
        studentEntity.setStudentName(studentDto.getStudentName());
        studentEntity.setStudentEmail(studentDto.getStudentEmail());

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return new StudentDto(
                savedStudent.getId(),
                savedStudent.getStudentName(),
                savedStudent.getStudentEmail()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public StudentFeeDto getStudentTotalFee(int id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student does not exist with id " + id
                ));

        Long totalFee = student.getCourses().stream()
                .map(SubjectEntity::getCourseFee)
                .reduce(0L, Long::sum);

        return new StudentFeeDto(
                student.getId(),
                student.getStudentName(),
                totalFee
        );
    }

    @Transactional
    @Override
    public StudentCourseDetailsDto assignCourseToStudent(
            int studentId,
            int courseId
    ) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));

        SubjectEntity course = subjectRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"
                ));

        boolean alreadyAssigned = student.getCourses().stream()
                .anyMatch(existingCourse ->
                        existingCourse.getCourseId() == courseId
                );

        if (alreadyAssigned) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This course is already assigned to the student"
            );
        }

        student.getCourses().add(course);
        studentRepository.save(student);

        List<Integer> courseIds = student.getCourses().stream()
                .map(SubjectEntity::getCourseId)
                .toList();

        List<String> courseNames = student.getCourses().stream()
                .map(SubjectEntity::getCourseName)
                .toList();

        Long totalFee = student.getCourses().stream()
                .map(SubjectEntity::getCourseFee)
                .reduce(0L, Long::sum);

        return new StudentCourseDetailsDto(
                student.getId(),
                student.getStudentName(),
                courseIds,
                courseNames,
                totalFee
        );
    }

    @Transactional
    @Override
    public StudentCourseDetailsDto removeCourseToStudent(int studentId, int courseId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));

        SubjectEntity course = subjectRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"
                ));

        boolean isAssigned = student.getCourses().stream()
                .anyMatch(existingCourse -> existingCourse.getCourseId() == courseId);

        if (!isAssigned) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This course is not assigned to the student"
            );
        }

        student.getCourses().remove(course);
        studentRepository.save(student);

        List<Integer> courseIds = student.getCourses().stream()
                .map(SubjectEntity::getCourseId)
                .toList();

        List<String> courseNames = student.getCourses().stream()
                .map(SubjectEntity::getCourseName)
                .toList();

        Long totalFee = student.getCourses().stream()
                .map(SubjectEntity::getCourseFee)
                .reduce(0L, Long::sum);

        return new StudentCourseDetailsDto(
                student.getId(),
                student.getStudentName(),
                courseIds,
                courseNames,
                totalFee
        );
    }

    @Override
    public WeatherAPIResponse getWeatherData(double lat, double lon) throws Exception {
        OpenWeatherService openWeatherService = new OpenWeatherService();
        return openWeatherService.getWeatherData(lat, lon);
    }


}
