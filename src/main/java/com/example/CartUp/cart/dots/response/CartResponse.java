package com.example.CartUp.cart.dots.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private UUID userId;
    private double totalPrice;
    private List<CartItemResponse> cartItemList;
}
