package com.example.CartUp.cart.repositories;

import com.example.CartUp.cart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
