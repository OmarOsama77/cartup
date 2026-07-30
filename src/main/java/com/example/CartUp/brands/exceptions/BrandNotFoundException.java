package com.example.CartUp.brands.exceptions;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(Long id){
        super("Brand with id "+id+" not found");
    }
}
