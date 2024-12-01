package com.company.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    private final Map<String, Long> blacklistedTokens = new ConcurrentHashMap<>();
    private static final long TOKEN_EXPIRATION = 3600000; // 1 jam

    public void blacklistToken(String token) {
        blacklistedTokens.put(token, System.currentTimeMillis());
    }

    public boolean isTokenBlacklisted(String token) {
        Long blacklistedTime = blacklistedTokens.get(token);
        if (blacklistedTime != null) {
            // Cek apakah token masih dalam masa berlaku
            if (System.currentTimeMillis() - blacklistedTime < TOKEN_EXPIRATION) {
                return true;
            }
            // Hapus token kadaluarsa
            blacklistedTokens.remove(token);
        }
        return false;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void cleanupExpiredTokens() {
        long currentTime = System.currentTimeMillis();
        blacklistedTokens.entrySet().removeIf(
            entry -> currentTime - entry.getValue() > TOKEN_EXPIRATION
        );
    }
}