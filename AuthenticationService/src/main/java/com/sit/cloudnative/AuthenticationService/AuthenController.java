package com.sit.cloudnative.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
public class AuthenController {
    @Autowired
    TokenService tokenService;

    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestParam(name = "studentId", required = true) String studentId,
            @RequestParam(name = "password", required = true) String password
            ) {
        Student student = studentService.findStudentByCredential(studentId, password);
        if(student != null) {
            AuthResponse authResponse = tokenService.createToken(student);
            return new ResponseEntity(authResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getStudent (
            @RequestHeader(name = "Authorization", required = true) String token
    ) {
        Student student = this.getUserFromToken(token);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestHeader(name = "Authorization", required = true) String token
    ) {
        Student student = this.getUserFromToken(token);
        AuthResponse authResponse = tokenService.createToken(student);
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
    }

    private Student getUserFromToken(String token) {
        String studentId = tokenService.getIdFromToken(token);
        Student student = studentService.findById(studentId);
        return student;
    }
}
