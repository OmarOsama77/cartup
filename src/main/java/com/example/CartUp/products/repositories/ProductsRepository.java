package com.example.CartUp.products.repositories;

import com.example.CartUp.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Long> {
}
