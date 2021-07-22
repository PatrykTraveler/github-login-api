package com.github.loginapi.service;

import com.github.loginapi.model.User;
import com.github.loginapi.repository.AuditRepository;
import com.github.loginapi.service.client.GithubApiClient;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private final AuditRepository auditRepository;
    private final GithubApiClient githubApiClient;
    private final BusinessGithubUserConverter businessGithubUserConverter;

    public LoginService(AuditRepository auditRepository, GithubApiClient githubApiClient, BusinessGithubUserConverter businessGithubUserConverter) {
        this.auditRepository = auditRepository;
        this.githubApiClient = githubApiClient;
        this.businessGithubUserConverter = businessGithubUserConverter;
    }

    public User getUser(String userLogin) {
        final var user = githubApiClient.getUser(userLogin);
        auditRepository.incrementRequestCount(userLogin);
        return businessGithubUserConverter.convertGithubUserToUser(user);
    }
}
