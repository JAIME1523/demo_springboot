package com.example.demo.common.Errors;

public class ErrorsHttp {
    private final String message;
    private final int status;

    public ErrorsHttp(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
}
