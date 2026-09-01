package com.example.CartUp.product.services;

import com.example.CartUp.attribute.entities.AttributeValue;
import com.example.CartUp.attribute.services.AttributeValueService;
import com.example.CartUp.inventory.entities.Inventory;
import com.example.CartUp.inventory.services.InventoryService;
import com.example.CartUp.product.dtos.request.UploadProductVariantRequest;
import com.example.CartUp.product.dtos.response.ProductVariantDto;
import com.example.CartUp.product.entities.ProductVariant;
import com.example.CartUp.product.mappers.ProductsMappers;
import com.example.CartUp.product.repositories.ProductVariantRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductVariantService {
    private final ProductService productService;
    private final AttributeValueService attributeValueService;
    private final ProductVariantRepository productVariantRepository;
    private final InventoryService inventoryService;

    public ProductVariantDto uploadProductVariant(UploadProductVariantRequest request, Long productId) {

        List<AttributeValue> attributeValues = attributeValueService.findAllById(request.getAttributes());

        if (isAttributesDuplicated(attributeValues)) {
            throw new ApplicationException(ErrorCode.DUPLICATED_ATTRIBUTES);
        }


        ProductVariant productVariant = ProductVariant
                .builder()
                .price(request.getPrice())
                .attributeValues(attributeValues)
                .product(productService.findById(productId))
                .build();

        ProductVariant saved = productVariantRepository.save(productVariant);
        Inventory inventory=  inventoryService.createInventory(saved,request.getAvailableQuantity());

        saved.setInventory(inventory);
        return ProductsMappers.fromProductVariantToProductVariantDto(productVariant);

    }

    public boolean isAttributesDuplicated(List<AttributeValue> attributesValues) {
        Set<String> attributesNames = new HashSet<>();

        for (int i=0;i<attributesValues.size();i++){
            if(attributesNames.contains(attributesValues.get(i).getAttribute().getName())){
                return true;
            }else{
                attributesNames.add(attributesValues.get(i).getAttribute().getName());
            }
        }

        return false;
    }


    public void deleteProductVariant(Long productId,Long productVariantId){

        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(()->new ApplicationException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        if(!productVariant.getProduct().getId().equals(productId)){
            throw new ApplicationException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);
        }

        productVariantRepository.deleteById(productVariantId);
    }


    public ProductVariant findById(Long id){
        return productVariantRepository.findById(id).orElseThrow(()->new ApplicationException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));
    }


}
