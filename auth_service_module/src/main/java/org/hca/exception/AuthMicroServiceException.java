package org.hca.exception;


import lombok.Getter;

@Getter
public class AuthMicroServiceException extends RuntimeException{
    private ErrorType errorType;

    public AuthMicroServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AuthMicroServiceException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}