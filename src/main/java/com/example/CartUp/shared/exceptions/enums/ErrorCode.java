package com.example.CartUp.shared.exceptions.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("Requested user does not exist", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("Email address is already registered", HttpStatus.CONFLICT),
    ATTRIBUTE_ALREADY_EXISTS("attribute already exists",HttpStatus.CONFLICT),
    ATTRIBUTE_NOT_FOUND("attribute not found",HttpStatus.NOT_FOUND),
    ATTRIBUTE_VALUE_ALREADY_EXISTS("attribute value already exists",HttpStatus.CONFLICT),
    ATTRIBUTE_VALUE_NOT_FOUND("attribute value not found",HttpStatus.NOT_FOUND);


    private String message;
    private HttpStatus status;

     ErrorCode( String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }



}
