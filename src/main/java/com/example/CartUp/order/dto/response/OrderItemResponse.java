package com.example.CartUp.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemResponse {
    private Long productVariantId;
    private String productName;
    private int quantity;
    private double subTotal;
}
