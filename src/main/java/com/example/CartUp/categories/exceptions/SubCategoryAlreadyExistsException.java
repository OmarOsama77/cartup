package com.example.CartUp.categories.exceptions;

public class SubCategoryAlreadyExistsException extends RuntimeException{
    public SubCategoryAlreadyExistsException(String subCatName){
        super("Sub Category "+subCatName +" already exists");
    }
}
