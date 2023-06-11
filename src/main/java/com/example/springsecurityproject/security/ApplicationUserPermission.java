package com.example.springsecurityproject.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("student:read"),
    COURSE_WRITE("student:write");

    ApplicationUserPermission(String permission) {
    }
}
