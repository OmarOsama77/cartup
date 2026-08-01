package com.example.CartUp.products.repositories;

import com.example.CartUp.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<Product,Long> {

    @Modifying
    @Query(value = "UPDATE products set product_status = 'ACTIVE' where id = :productId",nativeQuery = true)
    void updateProductStatusToActive(Long productId);

    boolean existsByNameAndSubCategory_IdAndBrand_Id(String name, Long subCategoryId, Long brandId);
}
