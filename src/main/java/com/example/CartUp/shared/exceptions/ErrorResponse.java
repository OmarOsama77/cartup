package com.example.CartUp.shared.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class ErrorResponse {
   private String message;

}
