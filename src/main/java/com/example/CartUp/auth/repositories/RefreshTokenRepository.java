package com.example.CartUp.auth.repositories;

import com.example.CartUp.auth.entities.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying

    @Query(value = "delete from refresh_tokens where expiry_date < :now", nativeQuery = true)
    void deleteExpiredTokens(@Param("now") Instant now);

    @Query(value = "select user_id from refresh_tokens where token = :token",nativeQuery = true)
    Optional<UUID> findUserIdByToken(@Param("token") String token);
}
