package com.jbm.portfolio.dto;


public class AuthResponse {

    private String token;
    private String type;

    public AuthResponse() {
    }

    public AuthResponse(String token, String type) {
        this.token = token;
        this.type = type;
  
       
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
    
    
    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    
    
    
    
    
}