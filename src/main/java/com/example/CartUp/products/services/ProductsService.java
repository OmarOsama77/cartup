package com.example.CartUp.products.services;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;

public interface ProductsService {
     CreateProductDtoResponse uploadProduct(CreateProductDtoRequest request);
}