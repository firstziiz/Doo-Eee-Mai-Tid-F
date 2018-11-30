package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Exception.UserNotFoundException;
import com.sit.cloudnative.AuthenticationService.Logger.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class AuthenController {

    AuditLogger logger = new AuditLogger(this.getClass().getName());

    @Autowired
    TokenService tokenService;

    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request
            ) {
        Student student = studentService.findStudentByCredential(userId, password);
        if (student == null) {
            logger.warn(request, userId + " login failed");
            throw new UserNotFoundException("No user found with this credential.");
        }
        AuthResponse authResponse = tokenService.createToken(student);
        logger.info(request, userId + " login success");
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getStudent (
            @RequestHeader(name = "Authorization", required = true) String token,
            HttpServletRequest request
    ) {
        Student student = this.getUserFromToken(token);
        logger.info(request, student.getId() + " get student data");
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestHeader(name = "Authorization", required = true) String token,
            HttpServletRequest request
    ) {
        Student student = this.getUserFromToken(token);
        AuthResponse authResponse = tokenService.createToken(student);
        logger.info(request, student.getId() + " refresh token");
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
    }

    private Student getUserFromToken(String token) {
        String studentId = tokenService.getIdFromToken(token);
        Student student = studentService.findById(studentId);
        return student;
    }
}
