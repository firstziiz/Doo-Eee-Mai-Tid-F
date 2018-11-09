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
    @Value("${authenservice.jwt.expiresec}")
    private long EXPIRESEC;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestParam(name = "studentId", required = true) String studentId,
            @RequestParam(name = "password", required = true) String password
            ) {
        AuthResponse authResponse = new AuthResponse();
        if(studentService.isCredentialCorrect(studentId, password)) {
            Date expDate = new Date(System.currentTimeMillis() + (EXPIRESEC * 1000));
            Student student = studentService.findById(studentId);
            String token = tokenService.createToken(student, expDate);
            authResponse.setToken("Bearer " + token);
            authResponse.setExpiryDate(expDate);
            return new ResponseEntity(authResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity(authResponse, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getStudent (
            @RequestHeader(name = "Authorization", required = true) String authToken
    ) {
        authToken = authToken.substring(7);
        String studentId = tokenService.getIdFromToken(authToken);
        Student student = studentService.findById(studentId);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}
