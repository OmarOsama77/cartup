package com.example.CartUp.cart.controller;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.cart.dots.request.AddProductRequest;
import com.example.CartUp.cart.dots.request.UpdateQuantityRequest;
import com.example.CartUp.cart.dots.response.CartItemResponse;
import com.example.CartUp.cart.dots.response.CartResponse;
import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping("/items")
    public ResponseEntity<CartItemResponse> addItemToCart(
            @AuthenticationPrincipal User user,
            @RequestBody AddProductRequest request
    ) {
        return ResponseEntity.ok(service.addProductToCart(user, request));

    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(service.getCart(user));
    }

    @PatchMapping("/items/{cartItemId}")
    public ResponseEntity<CartItemResponse> updateItemQuantity(
            @AuthenticationPrincipal User user,
           @Valid @RequestBody UpdateQuantityRequest request
    ){
        return ResponseEntity.ok(service.updateCartItemQuantity(user,request));
    }
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteProductVariant(
            @AuthenticationPrincipal User user,
            @PathVariable Long cartItemId
    ) {
        service.deleteProductFromCart(user,cartItemId);
        return ResponseEntity.noContent().build();
    }



}
