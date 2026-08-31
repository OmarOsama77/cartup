package com.example.CartUp.order.mappers;

import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.order.dto.response.CheckOutResponse;
import com.example.CartUp.order.dto.response.OrderItemResponse;
import com.example.CartUp.order.entities.Order;
import com.example.CartUp.order.entities.OrderItem;

import java.util.List;

public class OrderMappers {

    public static OrderItem fromCartItemToOrderItem(CartItem cartItem, Order order){
        return OrderItem
                .builder()
                .order(order)
                .productVariant(cartItem.getProductVariant())
                .quantity(cartItem.getQuantity())
                .build();
    }


    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem){
        return OrderItemResponse
                .builder()
                .productName(orderItem.getProductVariant().getProduct().getName())
                .productVariantId(orderItem.getProductVariant().getId())
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getQuantity() * orderItem.getProductVariant().getPrice())
                .build();
    }
    public static List<OrderItemResponse> toOrderItemResponse(List<OrderItem> orderItemList){
        return orderItemList.stream().map(OrderMappers::toOrderItemResponse).toList();
    }
    public static CheckOutResponse toCheckOutResponse(Order order){
        List<OrderItemResponse> orderItemList = toOrderItemResponse(order.getOrderItemList());
        System.out.println("ya omar "+orderItemList.size());
        return CheckOutResponse
                .builder()
                .orderId(order.getId())
                .totalAmount(order.getTotalPrice())
                .discountRate(order.getDiscountRate())
                .orderStatus(order.getStatus())
                .orderItemsList(orderItemList)
                .createdAt(order.getCreatedAt())
                .build();
    }

}
