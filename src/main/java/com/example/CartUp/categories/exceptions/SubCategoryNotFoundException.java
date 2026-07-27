package com.example.CartUp.categories.exceptions;

public class SubCategoryNotFoundException extends RuntimeException{
    public SubCategoryNotFoundException(Long subCatId){
        super("Sub category with id "+subCatId+" doesn't exists");
    }
}
