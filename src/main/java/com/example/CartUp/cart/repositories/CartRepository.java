package com.example.CartUp.cart.repositories;

import com.example.CartUp.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUserId(UUID userId);
}
