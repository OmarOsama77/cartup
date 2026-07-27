package com.example.CartUp.shared.exceptions;

import com.example.CartUp.auth.exceptions.InvalidRefreshTokenException;
import com.example.CartUp.auth.exceptions.LoginFailedException;
import com.example.CartUp.auth.exceptions.UserAlreadyExistException;
import com.example.CartUp.auth.exceptions.UserNotFoundException;
import com.example.CartUp.categories.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> userAlreadyExist(
            UserAlreadyExistException exception
    ){
        Map<String,String> error = new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<Map<String,String>> loginFailed(
            LoginFailedException exception
    ){
        Map<String,String> error = new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<Map<String,String>> refresh(InvalidRefreshTokenException exception){
        Map<String,String> error = new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExists(
            CategoryAlreadyExistsException exception
    ){
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(errorResponse);
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
            CategoryNotFoundException exception
    ){
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException exception
    ){
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
    }

    @ExceptionHandler(SubCategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleSubCatAlreadyExistsException(
            SubCategoryAlreadyExistsException exception
    ){
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(errorResponse);
    }
    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubCategoryNotFoundException(
            SubCategoryNotFoundException exception
    ){
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
    }

}