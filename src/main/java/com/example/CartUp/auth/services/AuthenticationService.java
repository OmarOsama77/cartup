package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dtos.deleteuser.DeleteUserResponse;
import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenRequest;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenResponse;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.enums.Role;

import java.util.UUID;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request, Role role);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
    DeleteUserResponse deleteUser(UUID userId);
}
