package com.github.loginapi.repository;


import com.github.loginapi.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
class AuditRepositoryShould {
    private static final String LOGIN = "Login";

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    void incrementRequestCount() {
        //when
        auditRepository.incrementRequestCount(LOGIN);

        //then
        assertThat(getRequestCount()).isEqualTo(1);
    }

    private long getRequestCount() {
        final var parameters = new MapSqlParameterSource();
        parameters.addValue("login", LOGIN);

        final RowMapper<Long> rowMapper = (rs, rowNum) -> rs.getLong("request_count");

        return jdbcTemplate.queryForObject("SELECT request_count FROM audit WHERE login = :login", parameters, rowMapper);
    }
}
