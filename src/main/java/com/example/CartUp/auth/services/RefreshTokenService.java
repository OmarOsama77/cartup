package com.example.CartUp.auth.services;

import com.example.CartUp.auth.entities.RefreshToken;
import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.repositories.RefreshTokenRepository;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${app.security.jwt.refresh-expiration}")
    private long refreshExpiration;


    private final RefreshTokenRepository refreshRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshRepository, UserRepository userRepository) {
        this.refreshRepository = refreshRepository;
        this.userRepository = userRepository;
    }

    public String createRefreshToken(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        RefreshToken refreshToken =
                RefreshToken.builder()
                        .user(user)
                        .token(UUID.randomUUID().toString())
                        .expiryDate(Instant.now().plusMillis(refreshExpiration)).build();
        refreshRepository.save(refreshToken);

        return refreshToken.getToken();

    }



    public boolean isRefreshTokenExpiredAndCleanup(String token) {
        RefreshToken refreshToken = refreshRepository.findByToken(token)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVALID_REFRESH_TOKEN));
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshRepository.delete(refreshToken);
            return true;
        }
        return false;
    }



    public void deleteInvalidRefreshTokens() {
        refreshRepository.deleteExpiredTokens(Instant.now());
    }


    public UUID extractUserIdFromToken(String token) {
        return refreshRepository.findUserIdByToken(token).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }


}
