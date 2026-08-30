package com.example.CartUp.order.mappers;

import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.cart.enums.Status;
import com.example.CartUp.order.dto.response.CheckOutResponse;
import com.example.CartUp.order.dto.response.OrderItemResponse;
import com.example.CartUp.order.entities.Order;
import com.example.CartUp.order.entities.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMappers {

    public static OrderItem fromCartItemToOrderItem(CartItem cartItem,Order order){
        return OrderItem
                .builder()
                .productVariant(cartItem.getProductVariant())
                .quantity(cartItem.getQuantity())
                .order(order)
                .build();
    }

}
