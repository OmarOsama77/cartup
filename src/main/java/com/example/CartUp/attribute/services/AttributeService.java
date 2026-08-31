package com.example.CartUp.attribute.services;

import com.example.CartUp.attribute.dtos.request.AttributeRequest;
import com.example.CartUp.attribute.dtos.response.AttributeResponse;
import com.example.CartUp.attribute.entities.Attribute;
import com.example.CartUp.attribute.mappers.AttributeMappers;
import com.example.CartUp.attribute.repositories.AttributeRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributeService {
    private final AttributeRepository repository;


    public AttributeResponse uploadAttribute(AttributeRequest request){
        request.setName(request.getName().toLowerCase());
       if(repository.existsByName(request.getName())){
           throw new ApplicationException(ErrorCode.ATTRIBUTE_ALREADY_EXISTS);
       }
        Attribute attribute = Attribute
                .builder()
                .name(request.getName())
                .build();
        repository.save(attribute);
      return AttributeMappers.toAttributeResponse(attribute);
    }

    public AttributeResponse changeAttributeName(AttributeRequest request,Long attributeId){
        request.setName(request.getName().toLowerCase());
        Attribute attribute = repository.findById(attributeId)
                .orElseThrow(()->new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND));

        attribute.setName(request.getName());
        repository.save(attribute);
        return AttributeMappers.toAttributeResponse(attribute);
    }




    public void deleteAttribute(Long id){
        if(!repository.existsById(id)){
            throw new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND);
        }
        repository.deleteById(id);

    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
    public Attribute findAttributeById(Long id){
        return repository.findById(id).orElseThrow(()->new ApplicationException(ErrorCode.ATTRIBUTE_NOT_FOUND));
    }
}
