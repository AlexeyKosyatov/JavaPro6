package ru.stepup.javapro.javapro5.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stepup.javapro.javapro5.exception.LimitErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final String CODE = "Code";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public LimitErrorResponse handleThrowable(Throwable t) {
        var message = String.format("%s: %s", t.getClass().getName(), t.getMessage());
        return new LimitErrorResponse(CODE, message);
    }
}
