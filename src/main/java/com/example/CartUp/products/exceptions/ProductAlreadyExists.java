package com.example.CartUp.products.exceptions;

public class ProductAlreadyExists extends RuntimeException{

    public ProductAlreadyExists(String name){
        super("Product "+name+" already exists");
    }
}
