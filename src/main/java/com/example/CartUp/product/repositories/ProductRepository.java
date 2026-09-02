package com.example.CartUp.product.repositories;

import com.example.CartUp.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


    boolean existsByNameAndSubCategory_IdAndBrand_Id(String name, Long subCategoryId, Long brandId);


}
