package com.example.CartUp.auth.mappers;

import com.example.CartUp.auth.dto.response.GetUserResponse;
import com.example.CartUp.auth.dto.response.LoginResponse;
import com.example.CartUp.auth.dto.response.RefreshTokenResponse;
import com.example.CartUp.auth.dto.response.RegisterResponse;
import com.example.CartUp.auth.entities.User;

import java.util.UUID;

public class AuthMappers {
    public static RegisterResponse toRegisterResponse(UUID userId){
        return RegisterResponse
                .builder()
                .userId(userId)
                .message("Account added Successfully")
                .build();
    }
    public static LoginResponse toLoginResponse(String accessToken,String refreshToken){
        return LoginResponse.builder()
                .refreshToken(refreshToken).accessToken(accessToken).build();
    }
    public static RefreshTokenResponse toRefreshTokenResponse(String accessToken,String refreshToken){
        return RefreshTokenResponse.builder().refreshToken(refreshToken).accessToken(accessToken).build();
    }
    public static GetUserResponse toGetUserResponse(User user){
        return GetUserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .build();
    }
}
