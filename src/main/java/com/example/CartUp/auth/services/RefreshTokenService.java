package com.example.CartUp.auth.services;

import com.example.CartUp.auth.entities.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService {
    public String createRefreshToken(String userEmail);
    public boolean isRefreshTokenExpired(String token);
    public void deleteInvalidRefreshTokens();
    public UUID extractUserIdFromToken(String token);
}
