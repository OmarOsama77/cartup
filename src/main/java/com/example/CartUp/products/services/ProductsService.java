package com.example.CartUp.products.services;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.dtos.GetProductDtoResponse;
import com.example.CartUp.products.dtos.ProductVarietiesDtoRequest;

public interface ProductsService {
     CreateProductDtoResponse uploadProduct(CreateProductDtoRequest request);
     void deleteProduct(Long productId);
     GetProductDtoResponse addProductVarieties(ProductVarietiesDtoRequest request, Long productId);
}