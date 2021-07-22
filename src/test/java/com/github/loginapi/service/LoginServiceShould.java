package com.github.loginapi.service;

import com.github.loginapi.model.GithubUser;
import com.github.loginapi.model.User;
import com.github.loginapi.repository.AuditRepository;
import com.github.loginapi.service.client.GithubApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class LoginServiceShould {
    private static final String LOGIN = "Login";

    @Mock
    private GithubApiClient apiClient;
    @Mock
    private BusinessGithubUserConverter converter;
    @Mock
    private AuditRepository auditRepository;
    @InjectMocks
    private LoginService loginService;

    @Mock
    private GithubUser githubUser;
    @Mock
    private User user;

    @Test
    void returnUser() {
        //given
        given(apiClient.getUser(LOGIN)).willReturn(githubUser);
        given(converter.convertGithubUserToUser(githubUser)).willReturn(user);

        //when
        final var result = loginService.getUser(LOGIN);

        //then
        assertThat(result).isEqualTo(user);
        verify(auditRepository).incrementRequestCount(LOGIN);
        verifyNoMoreInteractions(auditRepository);
    }
}
