package com.example.CartUp.product.repositories;

import com.example.CartUp.product.entities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {

    @Query(value = "SELECT * FROM product_variants WHERE product_id = :productId", nativeQuery = true)
    List<ProductVariant> getProductVariants(@Param("productId") Long productId);
}
