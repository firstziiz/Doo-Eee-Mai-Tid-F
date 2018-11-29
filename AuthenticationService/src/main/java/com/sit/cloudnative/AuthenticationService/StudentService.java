package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Student findById(String studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }

    @Override
    public Student findStudentByCredential(String studentId, String password) {
        Student student = this.findById(studentId);
        if (student != null && studentId.equals(student.getId()) && passwordEncoder.matches(password, student.getPassword())) {
            return student;
        }
        return null;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
