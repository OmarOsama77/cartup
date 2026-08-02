package com.example.CartUp.attributes.services.impl;

import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueRequest;
import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueResponse;
import com.example.CartUp.attributes.entities.Attribute;
import com.example.CartUp.attributes.entities.AttributeValue;
import com.example.CartUp.attributes.repositories.AttributeValueRepository;
import com.example.CartUp.attributes.services.AttributeValueService;
import com.example.CartUp.attributes.services.AttributesService;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributeValueServiceImpl implements AttributeValueService {
    private final AttributesService attributesService;
    private final AttributeValueRepository repository;

    @Override
    public UploadAttributeValueResponse uploadAttributeValue(UploadAttributeValueRequest request,Long attributeId) {

        request.setValue(request.getValue().toLowerCase());
        if(repository.existsByValue(request.getValue())){
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_ALREADY_EXISTS);
        }
        Attribute attribute = attributesService.getAttributeById(attributeId);
        AttributeValue attributeValue = AttributeValue.builder().value(request.getValue()).attribute(attribute).build();
        AttributeValue saved = repository.save(attributeValue);
        return UploadAttributeValueResponse.builder().attributeValueId(saved.getId()).attributeValue(saved.getValue()).attributeId(saved.getAttribute().getId()).build();
    }

    @Override
    public void deleteAttributeValue(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new ApplicationException(ErrorCode.ATTRIBUTE_VALUE_NOT_FOUND);
        }
    }
}
