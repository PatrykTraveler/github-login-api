package com.github.loginapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {
    private final long id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final Instant createdAt;
    private final long publicRepos;
    private final long followers;

    public GithubUser(@JsonProperty("id") long id,
                      @JsonProperty("login") String login,
                      @JsonProperty("name") String name,
                      @JsonProperty("type") String type,
                      @JsonProperty("avatar_url") String avatarUrl,
                      @JsonProperty("created_at") Instant createdAt,
                      @JsonProperty("public_repos") long publicRepos,
                      @JsonProperty("followers") long followers) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.publicRepos = publicRepos;
        this.followers = followers;
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

    public long getPublicRepos() {
        return publicRepos;
    }

    public long getFollowers() {
        return followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubUser that = (GithubUser) o;
        return id == that.id && publicRepos == that.publicRepos && followers == that.followers && Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(avatarUrl, that.avatarUrl) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, type, avatarUrl, createdAt, publicRepos, followers);
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt=" + createdAt +
                ", publicRepos=" + publicRepos +
                ", followers=" + followers +
                '}';
    }
}
