package com.sit.cloudnative.AuthenticationService;

import com.sit.cloudnative.AuthenticationService.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User findStudentByCredential(String userId, String password) {
        User user = this.findById(userId);
        if (user != null && userId.equals(user.getId()) && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User save(User student) {
        return userRepository.save(student);
    }
}
