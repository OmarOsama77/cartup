package com.example.CartUp.cart.mappers;

import com.example.CartUp.cart.dots.response.CartItemResponse;
import com.example.CartUp.cart.dots.response.CartResponse;
import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.entities.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartMappers {
    public static CartItemResponse fromCartItemToCartItemResponse(CartItem cartItem) {
        return CartItemResponse
                .builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .subTotal(cartItem.getQuantity() * cartItem.getProductVariant().getPrice())
                .build();
    }

    public static CartResponse fromCartToCartResponse(Cart cart) {
        List<CartItemResponse> cartItemResponseList = cart.getCartItemList().stream().map(CartMappers::fromCartItemToCartItemResponse).collect(Collectors.toList());
        return CartResponse
                .builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .totalPrice(getCartPrice(cart))
                .cartItemList(cartItemResponseList)
                .build();
    }

    public static double getCartPrice(Cart cart) {
        double price = 0;
        for (int i = 0; i < cart.getCartItemList().size(); i++) {
            price += cart.getCartItemList().get(i).getQuantity() * cart.getCartItemList().get(i).getProductVariant().getPrice();
        }
        return price;
    }
}
