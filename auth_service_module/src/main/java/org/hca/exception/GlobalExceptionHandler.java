package org.hca.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// Bu sınıf tüm controller sınıfları için merkezi bir şekilde hata yönetimi sağlayacaktır.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)// RuntimeException Hata yakalayıcı bir metod olduğunu belirtmek için.
    public ResponseEntity<String> handleException(RuntimeException ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.badRequest()
                .body("Uygulamada RunTime Exception oluştu................" + ex.getMessage());
    }


    @ExceptionHandler(AuthMicroServiceException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(AuthMicroServiceException ex) {
        ErrorType errorType = ex.getErrorType();
        return new ResponseEntity(createErrorMessage(ex,
                errorType),
                errorType.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(Exception ex, ErrorType errorType) {
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,
                errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,
                errorType.getHttpStatus());
    }


}