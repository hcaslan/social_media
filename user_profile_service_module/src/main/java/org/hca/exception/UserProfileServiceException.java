package org.hca.exception;


import lombok.Getter;

@Getter
public class UserProfileServiceException extends RuntimeException{
    private ErrorType errorType;

    public UserProfileServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UserProfileServiceException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}