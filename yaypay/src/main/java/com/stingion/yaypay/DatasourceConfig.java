/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2020
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package com.stingion.yaypay;

/*
 * Author : Pavlo Omelianchuk
 * Date Created: 2020/08/26
 */

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class DatasourceConfig {

//    @Profile("!test")
    @Primary
    @Bean(name = "primary")
    public DataSource primaryDataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.url}") String jdbcUrl,
            @Value("${spring.datasource.username}") String userName,
            @Value("${spring.datasource.password}") String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setIdleTimeout(30);
        hikariConfig.setValidationTimeout(30000);
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
    }

//    @Primary
//    @Bean(name = "entityManagerFactory2")
//    public EntityManagerFactory getEntityManagerFactory(@Qualifier("primary") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setPersistenceUnitName("default");
//        emf.setDataSource(dataSource);
//        return emf.getObject();
//    }

    @Primary
    @Bean("mysqlEntityManagerFactory")
    public EntityManagerFactory mysqlEntityManagerFactory(
            @Qualifier("primary") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(getJpaHibernateVendorAdapter());
        em.setPersistenceUnitName("other");
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public HibernateJpaVendorAdapter getJpaHibernateVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("com.stingion.yaypay.data.repository.ExasolDialect");
        return adapter;
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("primary") DataSource dataSource,
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
