package com.example.CartUp.order.service;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.cart.enums.Status;
import com.example.CartUp.cart.services.CartService;
import com.example.CartUp.inventory.services.InventoryService;
import com.example.CartUp.order.dto.response.CheckOutResponse;
import com.example.CartUp.order.entities.Order;
import com.example.CartUp.order.entities.OrderItem;
import com.example.CartUp.order.mappers.OrderMappers;
import com.example.CartUp.order.repositories.OrderItemRepository;
import com.example.CartUp.order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final InventoryService inventoryService;
    private final CartService cartService;


    @Transactional
    public CheckOutResponse checkOut(User user) {
        Cart cart = cartService.getUserCart(user.getId());

        Order order = Order
                .builder()
                .user(user)
                .status(Status.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .discountRate(0)
                .build();
        List<OrderItem> orderItemList = cart.getCartItemList().stream().map(item ->
                OrderMappers.fromCartItemToOrderItem(item, order)
        ).toList();


        inventoryService.decreaseInventory(cart.getCartItemList());
        order.setOrderItemList(orderItemList);
        order.setTotalPrice(calcTotal(cart.getCartItemList(), 0));
        orderRepository.save(order);
        cartService.clearCart(user.getId());

        System.out.println("starting from here ya omar");
        return  OrderMappers.toCheckOutResponse(order);
    }

    private double calcTotal(List<CartItem> cartItemList, double discountRate) {
        double total = 0;
        for (CartItem cartItem : cartItemList) {
            total += cartItem.getQuantity() * cartItem.getProductVariant().getPrice();
        }

        total -= (total / 100) * discountRate;
        return total;
    }

}
