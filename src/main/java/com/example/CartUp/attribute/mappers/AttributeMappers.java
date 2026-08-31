package com.example.CartUp.attribute.mappers;

import com.example.CartUp.attribute.dtos.response.AttributeResponse;
import com.example.CartUp.attribute.dtos.response.AttributeValueResponse;
import com.example.CartUp.attribute.entities.Attribute;
import com.example.CartUp.attribute.entities.AttributeValue;

public class AttributeMappers {
    public static AttributeResponse toAttributeResponse(Attribute attribute){
        return AttributeResponse
                .builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .build();
    }
}
