package com.sit.cloudnative.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/student")
    public ResponseEntity<Student> create(
            @RequestParam(name = "studentId", required = true) String studentId,
            @RequestParam(name = "fullName", required = true) String fullName,
            @RequestParam(name = "faculty", required = true) String faculty,
            @RequestParam(name = "major", required = true) String major,
            @RequestParam(name = "password", required = true) String password
    ) {
        Student student = new Student();
        student.setId(studentId);
        student.setFullName(fullName);
        student.setFaculty(faculty);
        student.setMajor(major);
        student.setPassword(password);
        studentService.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }
}
