package com.example.CartUp.cart.dots.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemRequest {
    @NotNull(message = "product variant id is required")
    private Long productVariantId;
    @Positive(message = "quantity must be greater than 0")
    private int quantity;

}
