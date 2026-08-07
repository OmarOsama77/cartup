package com.example.CartUp.inventory.entities;

import com.example.CartUp.product.entities.ProductVariant;
import jakarta.persistence.*;

@Table(name = "inventory")
@Entity()
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    private int availableQuantity;

    private int reservedQuantity;

    @OneToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;
}
