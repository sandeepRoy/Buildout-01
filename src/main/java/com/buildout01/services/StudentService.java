package com.buildout01.services;

import com.buildout01.dtos.CreateStudentDto;
import com.buildout01.dtos.UpdateScoreDto;
import com.buildout01.entities.Student;
import com.buildout01.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // GET - List<Student>
    public List<Student> getAllStudentsLowToHigh() {
        List<Student> sorted = new ArrayList<>();
        List<Student> all = studentRepository.findAll();

        int max = 0;
        for(Student student : all){
            if(student.getScore() > max){
                max = Math.max(max, student.getScore());
                sorted.add(student);
            }
        }
        return sorted;
    }

    // GET - Student by ID
    public Student getStudentById(String student_id) {
        List<Student> all = studentRepository.findAll();
        for(Student student : all) {
            if(student.getId().equals(student_id)){
                return student;
            }
        }
        throw new RuntimeException("Student Not Found!");
    }

    // POST - Student
    public Student createStudent(CreateStudentDto studentDto) {

        List<Student> all = studentRepository.findAll();
        for(Student student : all) {
            if(student.getUsername().equals(studentDto.getUsername())){
                throw new RuntimeException("User Already Registered!");
            }
        }

        Student student = new Student();
        student.setUsername(studentDto.getUsername());
        student.setScore(studentDto.getScore());
        List<String> badgeList = student.getBadgeList();

        String badgeForScore = findBadgeForScore(studentDto.getScore());
        badgeList.add(badgeForScore);

        student.setBadgeList(badgeList);
        Student saved = studentRepository.save(student);
        return saved;
    }

    // PUT - Student score and badges updated by provided score and student_id

    public Student updateStudentScore(UpdateScoreDto updateScoreDto, String student_id) {
        List<Student> all = studentRepository.findAll();
        for(Student student : all) {
            // hit
            if(student.getId().equals(student_id)){
                List<String> badgeList = student.getBadgeList();

                if(badgeList.size() >= 3){
                    String[] badges = badgeList.toArray(new String[0]);
                    throw new RuntimeException("Assigned Badges: " + Arrays.toString(badges));
                }

                student.setScore(updateScoreDto.getScore());
                String new_badge = findBadgeForScore(updateScoreDto.getScore());
                badgeList.add(new_badge);

                student.setBadgeList(badgeList);

                Student updated = studentRepository.save(student);
                return updated;
            }
        }
        throw new RuntimeException("User Not Found!");
    }

    // DELETE - Remove Student by given student_id
    public String deleteStudentById(String student_id) {
        List<Student> all = studentRepository.findAll();
        for(Student student : all) {
            if(student.getId().equals(student_id)){
                studentRepository.delete(student);
                return "Deleted";
            }
        }
        throw new RuntimeException("User not Found!");
    }

    public static String findBadgeForScore(int score) {
        if(score >= 1 && score < 30) {
            return "Code Ninja";
        }
        if(score >= 30 && score < 60) {
            return "Code Champ";
        }
        if(score >= 60 && score <= 100) {
            return "Code Master";
        }
        return "Invalid Score";
    }
}
