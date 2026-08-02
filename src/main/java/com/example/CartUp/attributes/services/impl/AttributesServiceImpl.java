package com.example.CartUp.attributes.services.impl;

import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoRequest;
import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoResponse;
import com.example.CartUp.attributes.entities.Attribute;
import com.example.CartUp.attributes.repositories.AttributeRepository;
import com.example.CartUp.attributes.services.AttributesService;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributesServiceImpl implements AttributesService {

    private AttributeRepository repository;

    @Override
    public UploadAttributeDtoResponse uploadAttribute(UploadAttributeDtoRequest request) {
        request.setName(request.getName().toLowerCase());
        if(repository.existsByName(request.getName())){
            throw new ApplicationException(ErrorCode.ATTRIBUTE_ALREADY_EXISTS);
        }

        Attribute attribute = Attribute.builder().name(request.getName()).build();

        Attribute saved = repository.save(attribute);

        return UploadAttributeDtoResponse.builder().id(saved.getId()).name(saved.getName()).build();

    }

    @Override
    public Attribute getAttributeById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteAttribute(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }else{
            throw new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND);
        }

    }

}
