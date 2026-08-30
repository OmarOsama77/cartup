package com.example.CartUp.attribute.repositories;

import com.example.CartUp.attribute.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {
    boolean existsByName(String name);
}
