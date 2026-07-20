package com.example.CartUp.auth.services;

import com.example.CartUp.auth.entities.RefreshToken;

public interface RefreshTokenService {
    public String createRefreshToken(String userEmail);
    public RefreshToken verifyExpiration(String token);
    public void deleteInvalidRefreshTokens();

}
