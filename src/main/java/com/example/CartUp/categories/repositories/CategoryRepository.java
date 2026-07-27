package com.example.CartUp.categories.repositories;

import com.example.CartUp.categories.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByNameIgnoreCase(String name);
}
