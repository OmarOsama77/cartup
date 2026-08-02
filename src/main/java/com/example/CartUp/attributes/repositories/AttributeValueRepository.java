package com.example.CartUp.attributes.repositories;

import com.example.CartUp.attributes.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue,Long> {
    boolean existsByValue(String value);
}
