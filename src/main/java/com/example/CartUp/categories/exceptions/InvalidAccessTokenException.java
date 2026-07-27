package com.example.CartUp.categories.exceptions;

public class InvalidAccessTokenException extends RuntimeException{
    public InvalidAccessTokenException(){
        super("invalid access token");
    }
}
