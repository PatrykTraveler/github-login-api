package com.github.loginapi.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AuditRepository {
    private static final String REQUEST_COUNT_INCREMENT_QUERY = "INSERT INTO audit " +
            "(login, request_count) VALUES (:login, 1) " +
            "ON DUPLICATE KEY UPDATE request_count = request_count + 1";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuditRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void incrementRequestCount(String userLogin) {
        final var parameters = new MapSqlParameterSource();
        parameters.addValue("login", userLogin);

        jdbcTemplate.update(REQUEST_COUNT_INCREMENT_QUERY, parameters);
    }
}
