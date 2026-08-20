package com.example.CartUp.cart.entities;

import com.example.CartUp.product.entities.ProductVariant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int quantity;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

}
