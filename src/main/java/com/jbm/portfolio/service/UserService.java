//package com.jbm.portfolio.service;
//
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.jbm.portfolio.dto.RegisterRequest;
//import com.jbm.portfolio.exception.EmailAlreadyExistsException;
//import com.jbm.portfolio.model.Role;
//import com.jbm.portfolio.model.User;
//import com.jbm.portfolio.repository.UserRepository;
//
//import java.time.LocalDateTime;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    // Constructor injection
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public String register(RegisterRequest request) {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new EmailAlreadyExistsException("Email already registered.");
//        }
//
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);
//        user.setCreatedAt(LocalDateTime.now());
//
//        userRepository.save(user);
//
//        return "User registered successfully!";
//    }
//}


package com.jbm.portfolio.service;

import com.jbm.portfolio.dto.RegisterRequest;
import com.jbm.portfolio.model.User;
import com.jbm.portfolio.dto.UpdateProfileRequest;
import com.jbm.portfolio.dto.ChangePasswordRequest;

import java.util.List;

public interface UserService {

    String register(RegisterRequest request);

    User getProfile(String email);

    User updateProfile(String email, UpdateProfileRequest request);

    void changePassword(String email, ChangePasswordRequest request);

    List<User> getAllUsers();

    void deleteUser(Long id);
}





