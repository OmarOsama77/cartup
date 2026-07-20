package com.example.CartUp.shared.exceptions;

import com.example.CartUp.auth.exceptions.LoginFailedException;
import com.example.CartUp.auth.exceptions.UserAlreadyExistException;
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
}