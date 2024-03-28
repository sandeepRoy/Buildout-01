package com.buildout01.controllers;

import com.buildout01.dtos.CreateStudentDto;
import com.buildout01.dtos.UpdateScoreDto;
import com.buildout01.entities.Student;
import com.buildout01.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/user")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String user_id) {
        Student studentById = studentService.getStudentById(user_id);
        return new ResponseEntity<>(studentById, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        Student student = studentService.createStudent(createStudentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/user/{user_id}")
    public ResponseEntity<Student> updateStudentScore(@RequestBody UpdateScoreDto updateScoreDto, @PathVariable String user_id) {
        Student student = studentService.updateStudentScore(updateScoreDto, user_id);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String user_id) {
        String response = studentService.deleteStudentById(user_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
