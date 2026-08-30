package com.example.CartUp.cart.repositories;

import com.example.CartUp.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(UUID userId);

    @Modifying
    @Query("DELETE FROM carts c WHERE c.id = :cartId")
    void deleteCartById(@Param("cartId") Long cartId);

}
