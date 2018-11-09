package com.sit.cloudnative.AuthenticationService;

public interface StudentServiceInterface {
    public boolean isCredentialCorrect (String studentId, String password);
    public Student findById(String studentId);
}
