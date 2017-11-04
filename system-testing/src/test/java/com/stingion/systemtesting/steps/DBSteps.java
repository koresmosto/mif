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

    private int rowCount;

    @Given("JdbcTemplate instance")
    public void jdbcTemplate() {
        assertThat(jdbcTemplate).isNotNull();
    }

    @When("Get row count of table $table")
    public void entitiesCount(@Named("table") String table) {
        rowCount = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from " + table, Integer.class);
    }

    @Then("Row count of table is $rowCount")
    public void rowCount(@Named("rowCount") int count) {
        assertThat(rowCount).isEqualTo(count);
    }
}
