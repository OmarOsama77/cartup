package com.example.CartUp.auth.repositories;

import com.example.CartUp.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(value = "select email from users where id = :userId",nativeQuery = true)
    Optional<String> findUserEmailById(@Param("userId") UUID userId);
}
