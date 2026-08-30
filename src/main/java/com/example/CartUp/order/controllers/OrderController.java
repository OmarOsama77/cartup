package com.example.CartUp.order.controllers;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.order.dto.response.CheckOutResponse;
import com.example.CartUp.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/checkOut")
    public ResponseEntity<CheckOutResponse> checkOut(
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok(orderService.checkOut(user));
    }
}
