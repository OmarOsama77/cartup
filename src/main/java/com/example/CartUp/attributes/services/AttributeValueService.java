package com.example.CartUp.attributes.services;

import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueRequest;
import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueResponse;

public interface AttributeValueService {
    UploadAttributeValueResponse uploadAttributeValue(UploadAttributeValueRequest request,Long attributeId);
    void deleteAttributeValue(Long id);
}
