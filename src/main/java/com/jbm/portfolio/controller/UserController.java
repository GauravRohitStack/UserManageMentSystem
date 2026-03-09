package com.jbm.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jbm.portfolio.dto.AuthResponse;
import com.jbm.portfolio.dto.LoginRequest;
import com.jbm.portfolio.model.User;
import com.jbm.portfolio.repository.UserRepository;
import java.util.List;


import org.springframework.security.core.Authentication;
import com.jbm.portfolio.dto.ChangePasswordRequest;
import com.jbm.portfolio.dto.UpdateProfileRequest;
import com.jbm.portfolio.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

  
    
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        AuthResponse response = new AuthResponse("Login successful", "OK");
        return ResponseEntity.ok(response);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ✅ Get logged-in user profile
    @GetMapping("/profile")
    public User getProfile(Authentication authentication) {

        String email = authentication.getName();

        return userService.getProfile(email);
    }

    // ✅ Update profile
    @PutMapping("/profile")
    public User updateProfile(Authentication authentication,
                              @RequestBody UpdateProfileRequest request) {

        String email = authentication.getName();

        return userService.updateProfile(email, request);
    }

    // ✅ Change password
    @PutMapping("/change-password")
    public String changePassword(Authentication authentication,
                                 @RequestBody ChangePasswordRequest request) {

        String email = authentication.getName();

        userService.changePassword(email, request);

        return "Password changed successfully";
    }

    // ✅ Admin only - Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // ✅ Admin only - Delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
    
  
    // USER or ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}