package com.example.springsecurityproject.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Student {

    private final Integer id;
    private final String name;

    public Student(Integer id , String name){
        this.id = id;
        this.name = name;
    }

}
