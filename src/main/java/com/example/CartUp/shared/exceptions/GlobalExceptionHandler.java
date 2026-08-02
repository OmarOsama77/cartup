package com.example.CartUp.shared.exceptions;

import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import com.example.CartUp.shared.exceptions.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(
            ApplicationException exception
    ){
        ErrorCode errorCode = exception.getErrorCode();

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .build();
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}