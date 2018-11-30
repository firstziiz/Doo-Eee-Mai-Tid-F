package com.sit.cloudnative.AuthenticationService;

public interface UserServiceInterface {
    public User findStudentByCredential(String studentId, String password);

    public User findById(String studentId);
}
