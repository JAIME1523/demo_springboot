package com.example.demo.common.Errors;

import org.springframework.http.HttpStatus;

public class ExceptionClass extends Exception {
    private final String message;
    private final int statusCode;
    private final HttpStatus statusTYPE;

    public ExceptionClass(String message, HttpStatus statusTYPE) {
        this.message = message;
        this.statusCode = statusTYPE.value();
        this.statusTYPE = statusTYPE;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return statusCode;
    }
    public HttpStatus getHttpStatus() {
        return statusTYPE;
    }
}
