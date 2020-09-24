/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2020
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package com.stingion.yaypay.data.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exasol.containers.ExasolContainer;
import com.exasol.containers.ExasolContainerConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
//@TestExecutionListeners(value = FlywayTestExecutionListener.class, mergeMode = MERGE_WITH_DEFAULTS)
//@FlywayTest(invokeMigrateDB = false, invokeCleanDB = false)
@ContextConfiguration(initializers = ExasolContainerTest.Initializer.class)
public class ExasolContainerTest {
    @Container
    private static ExasolContainer<? extends ExasolContainer<?>> EXASOL_CONTAINER = new ExasolContainer<>
            (ExasolContainerConstants.EXASOL_DOCKER_IMAGE_REFERENCE)
            .withUsername("sys")
            .withPassword("exasol")
            .withLogConsumer(new Slf4jLogConsumer(log))
            ;

    @Container
    private static final MySQLContainer<?> MySQL_CONTAINER = new MySQLContainer<>()
            .withDatabaseName("db")
            .withUsername("sa")
            .withPassword("sa");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
//                    "exasol.datasource.url=" + EXASOL_CONTAINER.getJdbcUrl(),
//                    "exasol.datasource.password=" + EXASOL_CONTAINER.getPassword(),
//                    "exasol.datasource.username=" + EXASOL_CONTAINER.getUsername(),
//                    "exasol.datasource.driver-class-name=" + EXASOL_CONTAINER.getDriverClassName()
                    "spring.datasource.url=" + MySQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.password=" + MySQL_CONTAINER.getPassword(),
                    "spring.datasource.username=" + MySQL_CONTAINER.getUsername(),
                    "spring.datasource.driver-class-name=" + MySQL_CONTAINER.getDriverClassName(),
                    "spring.flyway.enabled=" + false
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    void test() {
        assertTrue(MySQL_CONTAINER.isRunning());
        System.out.println("============================");
        System.out.println(jdbcTemplate.queryForList("show databases"));
        System.out.println("============================");
//        assertTrue(jdbcTemplate.queryForList("show databases")
//                .stream().filter(e -> e.get("Database").equals("yaypay_test")).findAny().isPresent());
    }
}
