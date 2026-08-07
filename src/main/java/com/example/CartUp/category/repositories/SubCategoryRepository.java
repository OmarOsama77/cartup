package com.example.CartUp.category.repositories;

import com.example.CartUp.category.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    boolean existsByNameIgnoreCase(String name);
}
