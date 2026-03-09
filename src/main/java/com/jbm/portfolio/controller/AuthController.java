


package com.jbm.portfolio.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jbm.portfolio.dto.RegisterRequest;
import com.jbm.portfolio.service.AuthService;

//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    // Constructor Injection
//    public AuthController(UserService userService, JwtService jwtService) {
//        this.userService = userService;
//        this.jwtService = jwtService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//
//        User user = userService.registerUser(request);
//
//        String token = jwtService.generateToken(user);
//
//        return ResponseEntity.ok(
//                Map.of("token", token)
//        );
//    }



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//
//        String token = authService.register(request);
//
//        return ResponseEntity.ok(
//                Map.of("message", token)
//        );
//    }
    
    
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {

        String token = authService.register(request);

        return Map.of("token", token);
    }
    
    
}











