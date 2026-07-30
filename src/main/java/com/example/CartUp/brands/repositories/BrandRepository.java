package com.example.CartUp.brands.repositories;

import com.example.CartUp.brands.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    boolean existsByNameIgnoreCase(String name);
}
