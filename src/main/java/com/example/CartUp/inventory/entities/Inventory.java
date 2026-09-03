package com.example.CartUp.inventory.entities;

import com.example.CartUp.product.entities.ProductVariant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "inventory")
@Entity()
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long inventoryId;

    private int availableQuantity;



    @OneToOne

    @JoinColumn(name = "product_variant_id",unique = true)
    private ProductVariant productVariant;
}
