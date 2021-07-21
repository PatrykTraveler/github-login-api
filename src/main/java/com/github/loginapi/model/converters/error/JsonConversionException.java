package com.github.loginapi.model.converters.error;

public class JsonConversionException extends RuntimeException{
    public JsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
