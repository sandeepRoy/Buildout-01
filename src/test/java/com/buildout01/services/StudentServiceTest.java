package com.buildout01.services;

import com.buildout01.dtos.CreateStudentDto;
import com.buildout01.dtos.UpdateScoreDto;
import com.buildout01.entities.Student;
import com.buildout01.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @Test
    void getAllStudentsLowToHigh() {
        List<Student> allStudentsLowToHigh = studentService.getAllStudentsLowToHigh();
        assertEquals(allStudentsLowToHigh.size(), 3);
    }

    @Test
    void getStudentById() {
        String student_id = "";
        Student studentById = studentService.getStudentById(student_id);
        assertEquals(studentById.getUsername(), "Sandeep Roy");
    }

    @Test
    void createStudent() {
        CreateStudentDto createStudentDto = new CreateStudentDto();
        createStudentDto.setUsername("Name Surname");
        createStudentDto.setScore(15);

        Student created_student = studentService.createStudent(createStudentDto);
        assertEquals(created_student.getUsername(), createStudentDto.getUsername());
        assertEquals(created_student.getBadgeList().get(0), "Code Ninja");
    }

    @Test
    void updateStudentScore() {
        UpdateScoreDto updateScoreDto = new UpdateScoreDto();
        updateScoreDto.setScore(67);

        Student student_scoreUpdated = studentService.updateStudentScore(updateScoreDto, "66051740e8c4af2d916c186a");
        assertEquals(student_scoreUpdated.getBadgeList().get(student_scoreUpdated.getBadgeList().size() - 1), "Code Master");
    }

    @Test
    void deleteStudentById() {
        String s = studentService.deleteStudentById("66051740e8c4af2d916c186a");
        assertEquals(s, "Deleted");
    }
}