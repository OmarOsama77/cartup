package com.example.CartUp.category.exceptions;

public class SubCategoryNotFoundException extends RuntimeException{
    public SubCategoryNotFoundException(Long subCatId){
        super("Sub category with id "+subCatId+" doesn't exists");
    }
}
