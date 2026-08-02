package com.example.CartUp.attributes.services;

import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoRequest;
import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoResponse;
import com.example.CartUp.attributes.entities.Attribute;

public interface AttributesService {
    UploadAttributeDtoResponse uploadAttribute(UploadAttributeDtoRequest request);
    Attribute getAttributeById(Long id);
    void deleteAttribute(Long id);
}
