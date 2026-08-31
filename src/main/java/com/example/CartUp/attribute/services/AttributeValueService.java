package com.example.CartUp.attribute.services;

import com.example.CartUp.attribute.dtos.request.AttributeValueRequest;
import com.example.CartUp.attribute.dtos.response.AttributeValueResponse;
import com.example.CartUp.attribute.entities.AttributeValue;
import com.example.CartUp.attribute.mappers.AttributeMappers;
import com.example.CartUp.attribute.mappers.AttributeValueMappers;
import com.example.CartUp.attribute.repositories.AttributeValueRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttributeValueService {
    private final AttributeValueRepository repository;
    private final AttributeService attributeService;

    public AttributeValueResponse uploadAttributeValue(AttributeValueRequest request, Long attributeId) {
        if (!attributeService.existsById(attributeId)) {
            throw new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND);
        }
        request.setValue(request.getValue().toLowerCase());

        if (repository.existsByValue(request.getValue())) {
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_ALREADY_EXISTS);
        }


        AttributeValue attributeValue = AttributeValue
                .builder()
                .value(request.getValue())
                .attribute(attributeService.findAttributeById(attributeId))
                .build();
          repository.save(attributeValue);
        return AttributeValueMappers.toAttributeValueResponse(attributeValue);
    }

    public void deleteAttributeValue(Long attributeValueId) {
        if (!repository.existsById(attributeValueId)) {
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_NOT_FOUND);
        }
        repository.deleteById(attributeValueId);
    }


}
