package com.example.CartUp.auth.exceptions;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException(String cause){
        super(cause);
    }
}
