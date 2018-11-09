package com.sit.cloudnative.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentServiceInterface {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Student findById(String studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public boolean isCredentialCorrect(String studentId, String password) {
        try {
            Student student = studentRepository.findById(studentId).get();
            if (studentId.equals(student.getId()) && passwordEncoder.matches(password, student.getPassword())) {
                return true;
            }
        } catch (NullPointerException npe) {
            return false;
        }
        return false;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
