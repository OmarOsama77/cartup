package com.example.CartUp.cart.entities;

import jakarta.persistence.*;

@Table
@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
