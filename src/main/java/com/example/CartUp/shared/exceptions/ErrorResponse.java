package com.example.CartUp.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder

public class ErrorResponse {
   private String message;
   private HttpStatus status;
}
