/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.yaypay.data.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exasol.containers.ExasolContainer;
import com.exasol.containers.ExasolContainerConstants;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
//@ContextConfiguration(initializers = AnyRepository2Test.Initializer.class)
public class AnyRepository2Test {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>()
            .withDatabaseName("db")
            .withUsername("sa")
            .withPassword("sa");

    @Container
    private static ExasolContainer<? extends ExasolContainer<?>> EXASOL_CONTAINER = new ExasolContainer<>
            (ExasolContainerConstants.EXASOL_DOCKER_IMAGE_REFERENCE)
            .withUsername("sys")
            .withPassword("exasol")
            .withInitScript("exasol_init.sql")
            .withLogConsumer(new Slf4jLogConsumer(log));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.driver-class-name", MY_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.flyway.enabled", () -> false);
    }

    @Test
    public void testRunning() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
        assertTrue(EXASOL_CONTAINER.isRunning());
    }

    @Test
    public void test() {
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables")).isEqualTo(Collections.EMPTY_LIST);
    }

    @Sql(value = "classpath:db/migration/main/V1_0__Init_DB.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "classpath:db/migration/after/V1_0__Drop_User_Table.sql",
            executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void testWithSqlAnnotation() {
        assertEquals(3, jdbcTemplate.queryForList("select * from USER").size());
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables").size()).isEqualTo(1);
    }
}
