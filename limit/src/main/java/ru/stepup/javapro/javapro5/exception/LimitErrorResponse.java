package ru.stepup.javapro.javapro5.exception;

import java.time.OffsetDateTime;

public class LimitErrorResponse {
    private String code;
    private String message;
    private OffsetDateTime date;

    public LimitErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.date = OffsetDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
