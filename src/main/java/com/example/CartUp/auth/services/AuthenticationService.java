package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenDto;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenDto refreshToken(RefreshTokenDto request);
}
