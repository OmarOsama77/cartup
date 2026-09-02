package com.example.CartUp.product.services;

import com.example.CartUp.brand.entities.Brand;
import com.example.CartUp.brand.services.BrandService;
import com.example.CartUp.category.entities.SubCategory;
import com.example.CartUp.category.services.SubCategoryService;
import com.example.CartUp.product.dtos.request.ProductRequest;
import com.example.CartUp.shared.dto.PageResponse;
import com.example.CartUp.product.dtos.response.ProductResponse;
import com.example.CartUp.product.entities.Product;
import com.example.CartUp.product.mappers.ProductsMappers;
import com.example.CartUp.product.repositories.ProductRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import com.example.CartUp.shared.mappers.SharedMappers;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productsRepository;
    private BrandService brandService;
    private SubCategoryService subCategoryService;


    public PageResponse<ProductResponse> getProducts(Pageable pageable) {
        Page<Product> products = productsRepository.findAll(pageable);

        Page<ProductResponse> productDto =
                products.map(ProductsMappers::toProductDto);

        return SharedMappers.toPageResponse(productDto);

    }

    public ProductResponse uploadProduct(ProductRequest request) {
        Brand brand = brandService.findBrandById(request.getBrandId());
        SubCategory subCategory = subCategoryService.findSubCatById(request.getSubCatId());

        if (productsRepository.existsByNameAndSubCategory_IdAndBrand_Id(request.getName(), request.getSubCatId(), request.getBrandId())) {
            //already exists
            throw new ApplicationException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }

        //insert it
        Product product = Product
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .brand(brand)
                .subCategory(subCategory)
                .build();

        productsRepository.save(product);

        return ProductsMappers.toProductDto(product);
    }


    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND));
    }

}
