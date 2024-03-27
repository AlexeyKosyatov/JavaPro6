package ru.stepup.javapro.javapro5.rest;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stepup.javapro.javapro5.exception.LimitErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<LimitErrorResponse> handleRuntimeException(RuntimeException e) {
        var errorResponse = new LimitErrorResponse("CODE_ERROR", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<LimitErrorResponse> handleBadRequestException(BadRequestException e) {
        var errorResponse = new LimitErrorResponse("CODE_ERROR", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
