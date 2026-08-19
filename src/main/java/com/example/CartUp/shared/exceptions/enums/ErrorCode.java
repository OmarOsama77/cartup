package com.example.CartUp.shared.exceptions.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("Requested user does not exist", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("User address is already registered", HttpStatus.CONFLICT),
    LOGIN_FAILED("Invalid email or password",HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN("Invalid refresh token",HttpStatus.UNAUTHORIZED),
    EMAIL_NOT_FOUND("Email not found",HttpStatus.NOT_FOUND),
    BRAND_ALREADY_EXISTS("Brand already exists",HttpStatus.CONFLICT),
    BRAND_NOT_FOUND("Brand not found",HttpStatus.CONFLICT),
    CATEGORY_ALREADY_EXISTS("Category already exists",HttpStatus.CONFLICT),
    CATEGORY_NOT_FOUND("Category not found",HttpStatus.CONFLICT),
    SUB_CATEGORY_ALREADY_EXISTS("Sub category already exists",HttpStatus.CONFLICT),
    SUB_CATEGORY_NOT_FOUND("Sub category not found",HttpStatus.CONFLICT),
    PRODUCT_ALREADY_EXISTS("Product already exists",HttpStatus.CONFLICT),
    ATTRIBUTE_ALREADY_EXISTS("Attribute already exists",HttpStatus.CONFLICT),
    ATTRIBUTE_NOT_FOUND("Attribute not found",HttpStatus.NOT_FOUND),
    ATTRIBUTE_VALUE_ALREADY_EXISTS("Attribute value already exists",HttpStatus.CONFLICT),
    ATTRIBUTE_VALUE_NOT_FOUND("Attribute value not found",HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("Product not found",HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

     ErrorCode( String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }



}
