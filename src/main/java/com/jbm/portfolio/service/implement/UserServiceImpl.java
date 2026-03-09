package com.jbm.portfolio.service.implement;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jbm.portfolio.dto.RegisterRequest;
import com.jbm.portfolio.dto.UpdateProfileRequest;
import com.jbm.portfolio.dto.ChangePasswordRequest;
import com.jbm.portfolio.exception.EmailAlreadyExistsException;
import com.jbm.portfolio.model.Role;
import com.jbm.portfolio.model.User;
import com.jbm.portfolio.repository.UserRepository;
import com.jbm.portfolio.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public User getProfile(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateProfile(String email, UpdateProfileRequest request) {

        User user = getProfile(email);

        user.setName(request.getName());

        return userRepository.save(user);
    }

    @Override
    public void changePassword(String email, ChangePasswordRequest request) {

        User user = getProfile(email);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}