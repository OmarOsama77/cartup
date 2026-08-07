package com.example.CartUp.category.exceptions;

public class InvalidAccessTokenException extends RuntimeException{
    public InvalidAccessTokenException(){
        super("invalid access token");
    }
}
