package com.example.CartUp.order.dto.response;

import com.example.CartUp.cart.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutResponse {
    private Long orderId;
    private Status orderStatus;
    private int discountRate;
    private double totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItemsList;
}
