package com.example.CartUp.product.entities;

import com.example.CartUp.brand.entities.Brand;
import com.example.CartUp.category.entities.SubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductVariant> productVariantList;
}
