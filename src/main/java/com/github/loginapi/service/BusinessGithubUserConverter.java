package com.github.loginapi.service;

import com.github.loginapi.model.GithubUser;
import com.github.loginapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class BusinessGithubUserConverter {
    public User convertGithubUserToUser(GithubUser user) {
        final double calculation = 6.d / user.getFollowers() * (2 + user.getPublicRepos());
        return new User(user.getId(), user.getLogin(), user.getName(), user.getType(), user.getAvatarUrl(), user.getCreatedAt(), calculation);
    }
}
