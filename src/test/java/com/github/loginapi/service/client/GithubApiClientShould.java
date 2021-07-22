package com.github.loginapi.service.client;

import com.github.loginapi.model.GithubUser;
import com.github.loginapi.service.client.error.GithubApiClientException;
import com.github.loginapi.service.client.error.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GithubApiClientShould {
    private static final String LOGIN = "Login";

    @Mock
    private HttpClient httpClient;
    @InjectMocks
    private GithubApiClient githubApiClient;

    @Mock
    private GithubUser githubUser;
    @Mock
    private HttpResponse<GithubUser> httpResponse;

    @Captor
    private ArgumentCaptor<HttpRequest> requestArgumentCaptor;

    @Test
    void returnGithubUser() throws IOException, InterruptedException {
        //given
        given(httpResponse.body()).willReturn(githubUser);
        given(httpResponse.statusCode()).willReturn(200);
        given(httpClient.send(requestArgumentCaptor.capture(), ArgumentMatchers.<JsonBodyHandler<GithubUser>>any())).willReturn(httpResponse);

        //when
        final var result = githubApiClient.getUser(LOGIN);

        //then
        assertThat(result).isEqualTo(githubUser);
        final var actualRequest = requestArgumentCaptor.getValue();
        assertThat(actualRequest.uri().toString()).isEqualTo("https://api.github.com/users/Login");
    }

    @Test
    void handleNotFoundCase() throws IOException, InterruptedException {
        //given
        given(httpResponse.statusCode()).willReturn(404);
        given(httpClient.send(requestArgumentCaptor.capture(), ArgumentMatchers.<JsonBodyHandler<GithubUser>>any())).willReturn(httpResponse);

        //then
        assertThrows(UserNotFoundException.class, () -> githubApiClient.getUser(LOGIN));
    }

    @Test
    void handleAnyOtherErrorStatus() throws IOException, InterruptedException {
        //given
        given(httpResponse.statusCode()).willReturn(500);
        given(httpClient.send(requestArgumentCaptor.capture(), ArgumentMatchers.<JsonBodyHandler<GithubUser>>any())).willReturn(httpResponse);

        //then
        assertThrows(GithubApiClientException.class, () -> githubApiClient.getUser(LOGIN));
    }
}
