package com.example.CartUp.brands.exceptions;

public class BrandAlreadyExistsException extends RuntimeException{
    public BrandAlreadyExistsException(String name){
        super("Brand "+name+" already exists");
    }
}
