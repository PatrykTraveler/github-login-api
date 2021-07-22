package com.github.loginapi.controller;

import com.github.loginapi.model.Error;
import com.github.loginapi.model.User;
import com.github.loginapi.service.LoginService;
import com.github.loginapi.service.client.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(@PathVariable("login") String userLogin) {
        try {
            return new ResponseEntity<>(loginService.getUser(userLogin), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleUnexpected() {
        final var error = new Error("Failed to perform operation", "UNEXPECTED");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
