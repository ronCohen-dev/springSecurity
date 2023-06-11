package com.example.springsecurityproject.controllers;

import com.example.springsecurityproject.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1,"ron")
    , new Student(2,"maria") , new Student(3,"tal"));

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable Integer studentId){
        return STUDENTS.stream().filter(student -> studentId.equals(student.getId()))
                .findFirst().orElseThrow(()-> new IllegalArgumentException("Student " + studentId + " does not found"));
    }
}
