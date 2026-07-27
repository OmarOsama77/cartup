package com.example.CartUp.categories.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String catName){
        super("Category "+catName+" already exists");
    }
}
