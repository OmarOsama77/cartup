package com.example.CartUp.attributes.repositories;

import com.example.CartUp.attributes.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {
    boolean existsByName(String name);
}
