package com.example.CartUp.cart.dots.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemResponse {
    private Long id;
    private int quantity;
    private double subTotal;
}
