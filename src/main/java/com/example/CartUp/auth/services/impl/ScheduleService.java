package com.example.CartUp.auth.services.impl;

import com.example.CartUp.auth.services.RefreshTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final RefreshTokenService refreshTokenService;
    public ScheduleService(RefreshTokenService refreshTokenService){
        this.refreshTokenService = refreshTokenService;
    }
    @Scheduled(cron = "0 0 3 * * MON")
    public void deleteInvalidRefreshTokens() {
        System.out.println("omar im deleting a token");
        refreshTokenService.deleteInvalidRefreshTokens();

    }
}
