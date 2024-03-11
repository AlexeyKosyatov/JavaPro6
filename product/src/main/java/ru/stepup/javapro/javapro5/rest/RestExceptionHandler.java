package ru.stepup.javapro.javapro5.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stepup.javapro.javapro5.exception.ProductErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProductErrorResponse> handleRuntimeException(RuntimeException e) {
        var errorResponse = new ProductErrorResponse("CODE_ERROR", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
