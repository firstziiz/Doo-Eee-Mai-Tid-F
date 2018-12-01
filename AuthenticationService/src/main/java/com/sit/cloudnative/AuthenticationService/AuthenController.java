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
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "password", required = true) String password, HttpServletRequest request) {
        User user = userService.findStudentByCredential(userId, password);
        if (user == null) {
            logger.error(request, userId + " login failed");
            throw new UserNotFoundException("No user found with this credential.");
        }
        AuthResponse authResponse = tokenService.createToken(user);
        logger.info(request, userId + " login success");
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(@RequestHeader(name = "Authorization", required = true) String token,
            HttpServletRequest request) {
        User user = this.getUserFromToken(token);
        logger.info(request, user.getId() + " get student data");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestHeader(name = "Authorization", required = true) String token, HttpServletRequest request) {
        User user = this.getUserFromToken(token);
        AuthResponse authResponse = tokenService.createToken(user);
        logger.info(request, user.getId() + " refresh token");
        return new ResponseEntity(authResponse, HttpStatus.CREATED);
    }

    private User getUserFromToken(String token) {
        String userId = tokenService.getIdFromToken(token);
        User user = userService.findById(userId);
        return user;
    }
}
