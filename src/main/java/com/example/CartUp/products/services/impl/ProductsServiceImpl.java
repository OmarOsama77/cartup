package com.example.CartUp.products.services.impl;

import com.example.CartUp.brands.services.BrandService;
import com.example.CartUp.categories.services.SubCategoryService;
import com.example.CartUp.products.dtos.*;
import com.example.CartUp.products.entities.Product;
import com.example.CartUp.products.entities.ProductVariant;
import com.example.CartUp.products.enums.ProductStatus;
import com.example.CartUp.products.exceptions.ProductAlreadyExists;
import com.example.CartUp.products.exceptions.ProductNotFoundException;
import com.example.CartUp.products.mappers.ProductMappers;
import com.example.CartUp.products.repositories.ProductVariantRepository;
import com.example.CartUp.products.repositories.ProductsRepository;
import com.example.CartUp.products.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductVariantRepository productVariantRepository;
    private final BrandService brandService;
    private final SubCategoryService subCategoryService;

    @Override
    public CreateProductDtoResponse uploadProduct(CreateProductDtoRequest request) {
        //check if a product already exist

        if (productsRepository.existsByNameAndSubCategory_IdAndBrand_Id(request.getName(), request.getSubCatId(), request.getBrandId())) {
            throw new ProductAlreadyExists(request.getName());
        }

        //save directly
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDesc())
                .subCategory(subCategoryService.findSubCatById(request.getSubCatId()))
                .brand(brandService.findBrandById(request.getBrandId()))
                .productStatus(ProductStatus.DRAFTED).build();

        Product p = productsRepository.save(product);
        return CreateProductDtoResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .status(p.getProductStatus())
                .description(p.getDescription())
                .brandId(p.getBrand().getId())
                .subCategoryId(p.getSubCategory().getId())
                .build();
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productsRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        productsRepository.deleteById(productId);
    }

    @Override
    public ProductDto addProductVarieties(ProductVarietiesDtoRequest request, Long productId) {
        //first make sure this product exist
        if (!productsRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        Product product = productsRepository.findById(productId).orElseThrow();

        ProductVariant productVariant = ProductVariant.builder().product(product).price(request.getPrice()).attributes(request.getAttributes()).build();
        productVariantRepository.save(productVariant);
        if (product.getProductStatus() == ProductStatus.DRAFTED) {
            product.setProductStatus(ProductStatus.ACTIVE);
            productsRepository.updateProductStatusToActive(productId);
        }


        List<ProductVariantDto> productVariantDtoList = product.getProductVariantList().stream().map(it ->
                ProductVariantDto.builder().id(it.getId())
                        .price(it.getPrice())
                        .attributes(it.getAttributes()).build()
        ).toList();

        return ProductDto.builder()
                .id(productId)
                .name(product.getName())
                .desc(product.getDescription())
                .productStatus(product.getProductStatus())
                .brandId(product.getBrand().getId())
                .subCatId(product.getSubCategory().getId())
                .productVariantDtoList(productVariantDtoList)
                .build();
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productsRepository.getActiveProducts();

        return products.stream().map(
                it ->
                        ProductMappers.fromProductEntity(it)
        ).toList();
    }
}
