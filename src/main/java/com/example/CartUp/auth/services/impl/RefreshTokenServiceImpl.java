package com.example.CartUp.auth.services.impl;

import com.example.CartUp.auth.entities.RefreshToken;
import com.example.CartUp.auth.repositories.RefreshTokenRepository;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.services.RefreshTokenService;
import com.example.CartUp.shared.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${app.security.jwt.refresh-expiration}")
    private long refreshExpiration;


    private final RefreshTokenRepository refreshRepository;
    private final UserRepository userRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshRepository, UserRepository userRepository) {
        this.refreshRepository = refreshRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createRefreshToken(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        RefreshToken refreshToken =
                RefreshToken.builder()
                        .user(user)
                        .token(UUID.randomUUID().toString())
                        .expiryDate(Instant.now().plusMillis(refreshExpiration)).build();
        refreshRepository.save(refreshToken);


        return refreshToken.getToken();
    }


    @Override
    public RefreshToken verifyExpiration(String token) {
        RefreshToken refreshToken = refreshRepository.findByToken(token).orElseThrow();
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token is expired");
        }
        return refreshToken;
    }

    @Override
    public void deleteInvalidRefreshTokens() {
       refreshRepository.deleteExpiredTokens(Instant.now());
    }


}
