package com.github.loginapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.Instant;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private final long id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final Instant createdAt;
    private final double calculations;

    public User(long id, String login, String name, String type, String avatarUrl, Instant createdAt, double calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public double getCalculations() {
        return calculations;
    }
}
