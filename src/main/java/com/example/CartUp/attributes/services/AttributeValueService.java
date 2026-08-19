package com.example.CartUp.attributes.services;

import com.example.CartUp.attributes.dtos.request.UploadAttributeValueRequest;
import com.example.CartUp.attributes.dtos.response.UploadAttributeValueResponse;
import com.example.CartUp.attributes.entities.Attribute;
import com.example.CartUp.attributes.entities.AttributeValue;
import com.example.CartUp.attributes.repositories.AttributeValueRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributeValueService {
    private final AttributeValueRepository repository;
    private final AttributeService attributeService;

    public UploadAttributeValueResponse uploadAttributeValue(UploadAttributeValueRequest request, Long attributeId) {
        if (!attributeService.existsById(attributeId)) {
            throw new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND);
        }
        request.setValue(request.getValue().toLowerCase());

        if(repository.existsByValue(request.getValue())){
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_ALREADY_EXISTS);
        }


        AttributeValue attributeValue = AttributeValue
                .builder()
                .value(request.getValue())
                .attribute(attributeService.findAttributeById(attributeId))
                .build();
        AttributeValue saved = repository.save(attributeValue);
        return UploadAttributeValueResponse
                .builder()
                .id(saved.getId())
                .value(saved.getValue())
                .attributeName(saved.getAttribute().getName())
                .build();
    }

    public void deleteAttributeValue(Long attributeValueId){
        if(!repository.existsById(attributeValueId)){
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_NOT_FOUND);
        }
        repository.deleteById(attributeValueId);
    }
}
