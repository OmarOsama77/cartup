package com.example.CartUp.shared.exceptions;

import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
