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
import com.stingion.yaypay.data.repository.AnyRepository2Test.TestConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Collections;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
//@ContextConfiguration(initializers = AnyRepository2Test.Initializer.class)
@Import(TestConfig.class)
public class AnyRepository2Test {

    @Autowired
    private ApplicationContext applicationContext;

    @TestConfiguration
    public static class TestConfig {

//        @Primary
//        @DependsOn("dataSource")
//        @Bean("mysqlDatasource")
//        public DataSource mysqlDatasource(@Qualifier("dataSource") DataSource mysqlDatasource) {
//            return mysqlDatasource;
//        }

//        @Autowired
//        private ApplicationContext applicationContext;

//        @Primary
////        @DependsOn("scopedTarget.dataSource")
//        @Bean("mysqlDatasource")
//        public DataSource mysqlDatasource() {
////            return mysqlDatasource;
//            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setDriverClassName(MY_SQL_CONTAINER.getDriverClassName());
//            hikariConfig.setJdbcUrl(MY_SQL_CONTAINER.getJdbcUrl());
//            hikariConfig.setUsername(MY_SQL_CONTAINER.getUsername());
//            hikariConfig.setPassword(MY_SQL_CONTAINER.getPassword());
//
//            hikariConfig.setMaximumPoolSize(100);
//            hikariConfig.setMinimumIdle(10);
//            hikariConfig.setIdleTimeout(30);
//            hikariConfig.setValidationTimeout(30000);
//            hikariConfig.setConnectionTestQuery("SELECT 1");
//
//            return new HikariDataSource(hikariConfig);
//        }

        @Bean("exasolDatasource")
        public DataSource exasolDatasource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(EXASOL_CONTAINER.getDriverClassName());
            hikariConfig.setJdbcUrl(EXASOL_CONTAINER.getJdbcUrl() + ";schema=EXASOL_YAYPAY_TEST");
            hikariConfig.setUsername(EXASOL_CONTAINER.getUsername());
            hikariConfig.setPassword(EXASOL_CONTAINER.getPassword());

            hikariConfig.setMaximumPoolSize(100);
            hikariConfig.setMinimumIdle(10);
            hikariConfig.setIdleTimeout(30);
            hikariConfig.setValidationTimeout(30000);
            hikariConfig.setConnectionTestQuery("SELECT 1");

            return new HikariDataSource(hikariConfig);
        }

        @Bean(name="entityManagerFactory")
        public LocalSessionFactoryBean sessionFactory() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect", "com.stingion.yaypay.data.repository.ExasolDialect");

            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setHibernateProperties(properties);
            return sessionFactory;
        }

        @Bean
        public DataSourceConnectionProvider connectionProvider(@Qualifier("exasolDatasource") DataSource dataSource) {
            return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
        }

        @Bean
        public DefaultConfiguration configuration(DataSourceConnectionProvider connectionProvider) {
            Settings settings = new Settings();
            settings.setRenderNameStyle(RenderNameStyle.AS_IS);

            DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
            jooqConfiguration.set(connectionProvider);
            jooqConfiguration.set(settings);

            return jooqConfiguration;
        }

        @Bean
        public DefaultDSLContext dsl(DefaultConfiguration configuration) {
            return new DefaultDSLContext(configuration);
        }
    }

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
            .withInitScript("exasol_init_create_biz_IT.sql")
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

//    @Sql(value = "classpath:exasol_init_fill_biz_IT.sql", config = @SqlConfig(dataSource = "exasolDatasource"))
//    @Sql(value = "classpath:exasol_init_fill_biz_IT.sql")
    @Test
    public void testRunning() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
        assertTrue(EXASOL_CONTAINER.isRunning());
    }

//    @Sql(value = "classpath:exasol_init_fill_biz_IT.sql", config = @SqlConfig(dataSource = "exasolDatasource"))
    @Test
    public void test() {
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables")).isEqualTo(Collections.EMPTY_LIST);
    }

    @Sql(value = "classpath:db/migration/main/V1_0__Init_DB.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
//            ,config = @SqlConfig(dataSource = "primary", transactionManager = "transactionManager")
    )
//    @Sql(value = "classpath:db/migration/after/V1_0__Drop_User_Table.sql",
//            executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void testWithSqlAnnotation() {
        assertEquals(3, jdbcTemplate.queryForList("select * from USER").size());
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables").size()).isEqualTo(1);
    }
}
