package com.example.CartUp.products.services;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.dtos.ProductDto;
import com.example.CartUp.products.dtos.ProductVarietiesDtoRequest;

import java.util.List;

public interface ProductsService {
     CreateProductDtoResponse uploadProduct(CreateProductDtoRequest request);
     void deleteProduct(Long productId);
     ProductDto addProductVarieties(ProductVarietiesDtoRequest request, Long productId);

     List<ProductDto> getProducts();

}