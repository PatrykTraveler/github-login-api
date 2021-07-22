package com.github.loginapi.service;

import com.github.loginapi.model.GithubUser;
import com.github.loginapi.model.User;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessGithubUserConverterShould {
    private static final long ID = 1;
    private static final String LOGIN = "octocat";
    private static final String NAME = "monalisa octocat";
    private static final String TYPE = "User";
    private static final String AVATAR_URL = "https://github.com/images/error/octocat_happy.gif";
    private static final Instant CREATED_AT = Instant.parse("2008-01-14T04:33:35Z");
    private static final long PUBLIC_REPOS = 4;
    private static final long FOLLOWERS = 12;

    private final BusinessGithubUserConverter converter = new BusinessGithubUserConverter();

    @Test
    void convertGithubUserToUser() {
        //given
        final var githubUser = new GithubUser(ID,
                LOGIN,
                NAME,
                TYPE,
                AVATAR_URL,
                CREATED_AT,
                PUBLIC_REPOS,
                FOLLOWERS
        );
        final var expectedResult = new User(ID, LOGIN, NAME, TYPE, AVATAR_URL, CREATED_AT, 3.d);


        //when
        final var result = converter.convertGithubUserToUser(githubUser);

        //then
        assertThat(result).isEqualTo(expectedResult);
    }
}
