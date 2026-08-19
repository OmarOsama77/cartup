package com.example.CartUp.product.repositories;

import com.example.CartUp.product.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE products set product_status = 'ACTIVE' where id = :productId",nativeQuery = true)
    void updateProductStatusToActive(Long productId);

    boolean existsByNameAndSubCategory_IdAndBrand_Id(String name, Long subCategoryId, Long brandId);


    @Query(value = "select * from products where product_status ='ACTIVE'",nativeQuery = true)
    List<Product> getActiveProducts();
}
