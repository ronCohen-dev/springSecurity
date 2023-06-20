package com.example.springsecurityproject.controllers;

import com.example.springsecurityproject.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
@Slf4j
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1,"ron")
            , new Student(2,"maria") , new Student(3,"tal"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN , ROLE_ADMINTRAINEE')")
    public List<Student> getStudents(){return STUDENTS;}

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student registeredNewStudent(@RequestBody Student student){
        log.info("new student registered : {}", student);
        return student;
    }
    @DeleteMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Integer studentId){
        log.info(" student deleted , student id : {}", studentId);
    }
    @PutMapping("{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer studentId ,@RequestBody Student student){
        log.info(" student updated ... student id : {} , student after updated : {}", studentId , student.getName());
    }
}