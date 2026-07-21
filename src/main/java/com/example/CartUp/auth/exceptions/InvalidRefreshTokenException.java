package com.example.CartUp.auth.exceptions;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException(){
        super("Invalid refresh token");
    }
}
