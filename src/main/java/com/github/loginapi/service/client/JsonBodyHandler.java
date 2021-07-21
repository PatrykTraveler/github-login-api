package com.github.loginapi.service.client;

import com.github.loginapi.model.converters.JsonConverter;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler <T> implements HttpResponse.BodyHandler<T>{
    private final JsonConverter<T> jsonConverter;

    public JsonBodyHandler(JsonConverter<T> jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJSON();
    }

    private HttpResponse.BodySubscriber<T> asJSON() {
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                jsonConverter::fromJson);
    }
}
