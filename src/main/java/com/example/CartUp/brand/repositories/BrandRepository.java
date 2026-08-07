package com.example.CartUp.brand.repositories;

import com.example.CartUp.brand.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    boolean existsByNameIgnoreCase(String name);
}
