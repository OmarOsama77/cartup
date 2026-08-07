package com.example.CartUp.category.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String catName){
        super("Category "+catName+" already exists");
    }
}
