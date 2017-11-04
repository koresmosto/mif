package com.stingion.systemtesting.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class DBSteps {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int entityCount;

    @Given("JdbcTemplate instance")
    public void jdbcTemplate() {
        assertThat(jdbcTemplate).isNotNull();
    }

    @When("Call for row count of entity $entity")
    public void entitiesCount(@Named("entity") String entity) {
        entityCount = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from " + entity, Integer.class);
    }

    @Then("Row count of entity is $count")
    public void rowCount(@Named("count") int count) {
        assertThat(count).isEqualTo(entityCount);
    }
}
