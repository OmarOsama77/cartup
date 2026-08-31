package com.example.CartUp.attribute.mappers;

import com.example.CartUp.attribute.dtos.response.AttributeValueResponse;
import com.example.CartUp.attribute.entities.AttributeValue;

public class AttributeValueMappers {

    public static AttributeValueResponse toAttributeValueResponse(AttributeValue attributeValue){
        return AttributeValueResponse
                .builder()
                .id(attributeValue.getId())
                .value(attributeValue.getValue())
                .build();
    }
}
