package com.example.CartUp.category.exceptions;

public class SubCategoryAlreadyExistsException extends RuntimeException{
    public SubCategoryAlreadyExistsException(String subCatName){
        super("Sub Category "+subCatName +" already exists");
    }
}
