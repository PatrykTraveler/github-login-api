package com.github.loginapi.service.client;

import com.github.loginapi.model.GithubUser;
import com.github.loginapi.model.converters.GithubUserConverter;
import com.github.loginapi.service.client.error.GithubApiClientException;
import com.github.loginapi.service.client.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GithubApiClient {
    private final HttpClient httpClient;
    private final GithubUserConverter githubUserConverter;

    public GithubApiClient(HttpClient httpClient, GithubUserConverter githubUserConverter) {
        this.httpClient = httpClient;
        this.githubUserConverter = githubUserConverter;
    }

    public GithubUser getUser(String userLogin) {
        try {
            final var request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.github.com/users/" + userLogin))
                    .GET()
                    .build();

            final HttpResponse<GithubUser> response = httpClient.send(request, new JsonBodyHandler<>(githubUserConverter));

            return resolveResponse(response);

        } catch (URISyntaxException e) {
            throw new GithubApiClientException("Failed to parse request query", e);
        } catch (IOException | InterruptedException e) {
            throw new GithubApiClientException("Failed to perform request", e);
        }
    }

    private <T> T resolveResponse(HttpResponse<T> response) {
        final var status = response.statusCode();
        final var responseBody = response.body();

        if (status == HttpStatus.OK.value()) {
            return responseBody;
        } else if (status == HttpStatus.NOT_FOUND.value()) {
            throw new UserNotFoundException("User not found");
        } else {
            throw new GithubApiClientException("Failed to get resource");
        }
    }
}
