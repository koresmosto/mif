/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.yaypay.data.repository;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.annotation.FlywayTestExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest
@ActiveProfiles("test")
@FlywayTestExtension
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
@FlywayTest
public class SampleTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @FlywayTest
    @Test
    public void testDefault() {
        Assertions.assertThat(2).isEqualTo(jdbcTemplate.queryForList("select * from USER_TEST").size());
    }

    @FlywayTest(invokeMigrateDB = false)
    @Test
    public void testNoMigration() {
        Assertions.assertThat(new ArrayList<>()).isEqualTo(jdbcTemplate.queryForList("show tables"));
    }

    @FlywayTest(locationsForMigrate = {"db/migration/main"}, overrideLocations = true)
    @Test
    public void changeMigrationLocation() {
        Assertions.assertThat(3).isEqualTo(jdbcTemplate.queryForList("select * from USER").size());
    }

    @FlywayTest(locationsForMigrate = {"db/migration/test2"}, overrideLocations = true, flywayName = "flyway")
    @Test
    public void changeMigrationLocation2() {
        Assertions.assertThat(0).isEqualTo(jdbcTemplate.queryForList("select * from CAR_TEST").size());
    }
}
