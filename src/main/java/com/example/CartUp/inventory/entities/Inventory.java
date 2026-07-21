package com.example.CartUp.inventory.entities;

import jakarta.persistence.*;

@Table(name = "inventory")
@Entity()
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    private int availableQuantity;

    private int reservedQuantity;


}
