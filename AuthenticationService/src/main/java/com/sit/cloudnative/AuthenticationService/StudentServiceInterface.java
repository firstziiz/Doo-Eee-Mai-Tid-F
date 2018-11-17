package com.sit.cloudnative.AuthenticationService;

public interface StudentServiceInterface {
    public Student findStudentByCredential(String studentId, String password);
    public Student findById(String studentId);
}
