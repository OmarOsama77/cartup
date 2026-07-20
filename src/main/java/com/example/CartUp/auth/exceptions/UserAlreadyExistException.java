package com.example.CartUp.auth.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userEmail) {
        super("User "+userEmail+" already exist");
    }

}
