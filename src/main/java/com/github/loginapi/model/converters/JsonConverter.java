package com.github.loginapi.model.converters;

public interface JsonConverter <T>{
    T fromJson(String json);
}
