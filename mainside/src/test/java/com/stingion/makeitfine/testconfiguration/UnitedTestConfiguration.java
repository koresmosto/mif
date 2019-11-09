package com.stingion.makeitfine.testconfiguration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@TestConfiguration
@ImportResource("classpath:spring-test/context-test.xml")
@Import(ServiceTestConfiguration.class)
public class UnitedTestConfiguration {
}
