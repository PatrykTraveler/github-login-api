package com.github.loginapi.model;

public class Error {
    private final String message;
    private final String type;

    public Error(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
