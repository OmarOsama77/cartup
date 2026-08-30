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
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        //get user's cart
        Cart cart = cartService.getUserCart(user.getId());
        System.out.println("cart " + cart.getId());
        double totalAmount = 0;

        Order order = Order
                .builder()
                .discount(0.0)
                .user(user)
                .status(Status.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .build();


        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem c : cart.getCartItemList()) {
            int availableQuantity = inventoryService.getAvailableQuantity(c.getProductVariant().getId());
            if(availableQuantity < c.getQuantity()){
                throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
            }
            totalAmount += c.getQuantity() * c.getProductVariant().getPrice();
            orderItemList.add(OrderMappers.fromCartItemToOrderItem(c,order));
            inventoryService.decreaseInventory(c.getProductVariant().getId(),c.getQuantity());
        }
        order.setTotalPrice(totalAmount);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItemList);
        cartService.clearCart(user.getId());

        return null;
    }
}
