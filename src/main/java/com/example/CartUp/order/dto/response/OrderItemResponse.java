package com.example.CartUp.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Long productVariantId;
    private String productName;
    private int quantity;
    private double subTotal;
}
