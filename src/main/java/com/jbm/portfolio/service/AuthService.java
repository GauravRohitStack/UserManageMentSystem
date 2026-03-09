package com.jbm.portfolio.service;

import com.jbm.portfolio.dto.AuthResponse;
import com.jbm.portfolio.dto.LoginRequest;
import com.jbm.portfolio.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
