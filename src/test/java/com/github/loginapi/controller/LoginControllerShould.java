package com.github.loginapi.controller;

import com.github.loginapi.model.User;
import com.github.loginapi.service.LoginService;
import com.github.loginapi.service.client.error.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginControllerShould {
    private static final String USER_LOGIN = "Login";

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private User user;

    @Test
    void getUser() {
        //given
        final var expectedResponse = new ResponseEntity<>(user, HttpStatus.OK);
        given(loginService.getUser(USER_LOGIN)).willReturn(user);

        //when
        final var result = loginController.getUser(USER_LOGIN);

        //then
        assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    void returnNotFoundWhenUserDoesntExist() {
        //given
        final var expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        given(loginService.getUser(USER_LOGIN)).willThrow(UserNotFoundException.class);

        //when
        final var result = loginController.getUser(USER_LOGIN);

        //then
        assertThat(result).isEqualTo(expectedResponse);
    }
}
