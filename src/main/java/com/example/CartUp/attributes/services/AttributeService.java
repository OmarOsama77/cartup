package com.example.CartUp.attributes.services;

import com.example.CartUp.attributes.dtos.request.UploadAttributeRequest;
import com.example.CartUp.attributes.dtos.response.UploadAttributeResponse;
import com.example.CartUp.attributes.entities.Attribute;
import com.example.CartUp.attributes.repositories.AttributeRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributeService {
    private final AttributeRepository repository;


    public UploadAttributeResponse uploadAttribute(UploadAttributeRequest request){
        request.setName(request.getName().toLowerCase());
       if(repository.existsByName(request.getName())){
           throw new ApplicationException(ErrorCode.ATTRIBUTE_ALREADY_EXISTS);
       }
        Attribute attribute = Attribute
                .builder()
                .name(request.getName())
                .build();
      Attribute saved =  repository.save(attribute);
      return UploadAttributeResponse.builder().id(saved.getId()).name(saved.getName()).build();
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
