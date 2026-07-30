package com.example.CartUp.products.services.impl;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.entities.Product;
import com.example.CartUp.products.repositories.ProductsRepository;
import com.example.CartUp.products.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {



    @Override
    public CreateProductDtoResponse uploadProduct(CreateProductDtoRequest request) {
        return null;
    }
}
