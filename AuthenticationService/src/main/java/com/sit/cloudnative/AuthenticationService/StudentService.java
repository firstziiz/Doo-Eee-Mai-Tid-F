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
    public Student findStudentByCredential(String studentId, String password) {
        Student student = this.findById(studentId);
        if (studentId.equals(student.getId()) && passwordEncoder.matches(password, student.getPassword())) {
            return student;
        }
        return null;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
