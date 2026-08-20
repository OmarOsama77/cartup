package com.example.CartUp.cart.dots.request;

import lombok.Data;

@Data
public class AddProductRequest {
    private Long productVariantId;
    private int quantity;

}
