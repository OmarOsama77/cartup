package com.example.CartUp.product.services;

import com.example.CartUp.brand.services.BrandService;
import com.example.CartUp.category.services.SubCategoryService;
import com.example.CartUp.product.dtos.request.UploadProductRequest;
import com.example.CartUp.product.dtos.response.ProductDto;
import com.example.CartUp.product.entities.Product;
import com.example.CartUp.product.mappers.ProductsMappers;
import com.example.CartUp.product.repositories.ProductRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productsRepository;
    private BrandService brandService;
    private SubCategoryService subCategoryService;


    public List<ProductDto> getProducts() {
        List<Product> products = productsRepository.findAll();


        return products.stream().map(ProductsMappers::fromProductToProductDto).collect(Collectors.toList());

    }

    public ProductDto uploadProduct(UploadProductRequest request) {
        if (productsRepository.existsByNameAndSubCategory_IdAndBrand_Id(request.getName(), request.getSubCatId(), request.getBrandId())) {
            //already exists
            throw new ApplicationException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }
        //insert it
        Product product = Product
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .brand(brandService.findBrandById(request.getBrandId()))
                .subCategory(subCategoryService.findSubCatById(request.getSubCatId()))
                .build();

        Product saved = productsRepository.save(product);

        return ProductDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .brandId(saved.getBrand().getId())
                .subCatId(saved.getSubCategory().getId())
                .productVariants(null)
                .build();
    }

    public boolean existsById(Long id) {
        return productsRepository.existsById(id);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
