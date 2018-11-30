package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Logger.AuditLogger;
import com.sit.cloudnative.AuthenticationService.User.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    AuditLogger logger = new AuditLogger(this.getClass().getName());
    @Autowired
    UserService userService;

    @PostMapping("/student")
    public ResponseEntity<User> create(@RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "fullName", required = true) String fullName,
            @RequestParam(name = "faculty", required = false) String faculty,
            @RequestParam(name = "year", required = false, defaultValue = "0") int year,
            @RequestParam(name = "major", required = false) String major,
            @RequestParam(name = "password", required = true) String password, HttpServletRequest request) {
        User user = new User();
        user.setId(userId);
        user.setFullName(fullName);
        user.setFaculty(faculty);
        user.setMajor(major);
        user.setYear(year);
        user.setPassword(password);
        user.setRole(Role.STUDENT);
        userService.save(user);
        logger.info(request, userId + " create account success");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
