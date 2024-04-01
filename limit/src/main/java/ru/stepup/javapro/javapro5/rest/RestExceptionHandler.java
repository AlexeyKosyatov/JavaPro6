package ru.stepup.javapro.javapro5.rest;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stepup.javapro.javapro5.exception.LimitErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final String CODE = "Code";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<LimitErrorResponse> handleBadRequestException(BadRequestException e) {
        var errorResponse = new LimitErrorResponse(CODE, e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public LimitErrorResponse handleThrowable(Throwable t) {
        var message = String.format("%s: %s", t.getClass().getName(), t.getMessage());
        return new LimitErrorResponse(CODE, message);
    }
}
