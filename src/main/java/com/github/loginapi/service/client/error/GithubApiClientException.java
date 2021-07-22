package com.github.loginapi.service.client.error;

public class GithubApiClientException extends RuntimeException {
    public GithubApiClientException(String message) {
        super(message);
    }

    public GithubApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
