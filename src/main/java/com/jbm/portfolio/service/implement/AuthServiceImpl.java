
package com.jbm.portfolio.service.implement;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.jbm.portfolio.dto.AuthResponse;
import com.jbm.portfolio.dto.LoginRequest;
import com.jbm.portfolio.dto.RegisterRequest;
import com.jbm.portfolio.dto.RefreshTokenRequest.RefreshTokenRequest;
import com.jbm.portfolio.model.Role;
import com.jbm.portfolio.model.User;
import com.jbm.portfolio.model.RefreshToken.RefreshToken;
import com.jbm.portfolio.repository.UserRepository;
import com.jbm.portfolio.security.JwtService;
import com.jbm.portfolio.service.AuthService;
import com.jbm.portfolio.service.RefreshTokenService.RefreshTokenService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public AuthServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder,RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
//    public String register(RegisterRequest request) {
//
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        userRepository.save(user);
//
//        return "User registered successfully";
//    }

    
    public String register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return token;
    }
    
    
//    @Override
//    public AuthResponse login(LoginRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String token = jwtService.generateToken(user.getEmail());
//
//        return new AuthResponse(token, "Login successful");
//    }
    
    
    
    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateToken(user.getEmail());

//        RefreshToken refreshToken = RefreshToken.createRefreshToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken());
    } 
    
    
    
    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.verifyToken(request.getRefreshToken());

        String accessToken =
                jwtService.generateToken(refreshToken.getUser().getEmail());

        return new AuthResponse(accessToken, refreshToken.getToken());
    }
    
    
    
}










