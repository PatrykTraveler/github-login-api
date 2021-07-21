package com.github.loginapi.model.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.loginapi.model.GithubUser;
import com.github.loginapi.model.converters.error.JsonConversionException;
import org.springframework.stereotype.Component;

@Component
public class GithubUserConverter implements JsonConverter<GithubUser> {
    private final ObjectReader reader;

    public GithubUserConverter() {
        final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        reader = mapper.readerFor(GithubUser.class);
    }

    @Override
    public GithubUser fromJson(String json) {
        try {
            return reader.readValue(json);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Failed to convert json", e);
        }
    }
}
