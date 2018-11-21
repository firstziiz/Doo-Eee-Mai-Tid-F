package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Exception.UserNotFoundException;
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
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "password", required = true) String password
            ) {
        Student student = studentService.findStudentByCredential(userId, password);
        if (student == null) {
            throw new UserNotFoundException("No user found with this credential.");
        }
        AuthResponse authResponse = tokenService.createToken(student);
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
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
