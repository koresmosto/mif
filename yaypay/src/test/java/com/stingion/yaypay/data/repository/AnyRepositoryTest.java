/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.yaypay.data.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@TestInstance(Lifecycle.PER_METHOD)
public class AnyRepositoryTest {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>()
            .withDatabaseName("db")
            .withUsername("sa")
            .withPassword("sa");

    static {
        MY_SQL_CONTAINER.setPortBindings(Arrays.asList("3506:3306"));
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testRunning() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }

    @Test
    public void test() {
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables")).isEqualTo(Collections.EMPTY_LIST);
    }
}
