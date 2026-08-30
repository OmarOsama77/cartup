package com.example.CartUp.attribute.repositories;

import com.example.CartUp.attribute.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue,Long> {
    boolean existsByValue(String value);
}
