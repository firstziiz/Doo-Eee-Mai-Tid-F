package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Logger.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
    AuditLogger logger = new AuditLogger(this.getClass().getName());
    @Autowired
    StudentService studentService;
    @PostMapping("/student")
    public ResponseEntity<Student> create(
            @RequestParam(name = "studentId", required = true) String studentId,
            @RequestParam(name = "fullName", required = true) String fullName,
            @RequestParam(name = "faculty", required = true) String faculty,
            @RequestParam(name = "year", required = true) int year,
            @RequestParam(name = "major", required = true) String major,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request
    ) {
        Student student = new Student();
        student.setId(studentId);
        student.setFullName(fullName);
        student.setFaculty(faculty);
        student.setMajor(major);
        student.setYear(year);
        student.setPassword(password);
        studentService.save(student);
        logger.info(request, studentId + " create account success");
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }
}
