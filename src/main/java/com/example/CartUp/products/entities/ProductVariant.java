package com.example.CartUp.products.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "product_variants")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> attributes;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
