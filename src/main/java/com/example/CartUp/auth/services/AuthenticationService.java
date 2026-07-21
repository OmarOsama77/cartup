package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenRequest;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenResponse;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
