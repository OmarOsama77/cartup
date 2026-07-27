package com.example.CartUp.categories.repositories;

import com.example.CartUp.categories.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    boolean existsByNameIgnoreCase(String name);
}
