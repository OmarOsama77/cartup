package com.example.CartUp.order.repositories;

import com.example.CartUp.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
