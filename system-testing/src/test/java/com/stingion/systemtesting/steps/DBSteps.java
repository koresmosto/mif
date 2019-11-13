/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */
package com.stingion.systemtesting.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class DBSteps {

  @Autowired private JdbcTemplate jdbcTemplate;

  private int rowCount;
  private Map<String, Object> row;

  @Given("JdbcTemplate instance")
  public void jdbcTemplateInstance() {
    assertThat(jdbcTemplate).isNotNull();
  }

  @When("Get row count of table $table")
  public void getRowCountOfTable(@Named("table") String table) {
    rowCount = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from " + table, Integer.class);
  }

  @Then("Row count of table is $rowCount")
  public void rowCountOfTableIs(@Named("rowCount") int count) {
    assertThat(rowCount).isEqualTo(count);
  }

  @When("Get row of table $table with $id")
  public void rowsOfTable(@Named("table") String table, @Named("id") int id) {
    row = this.jdbcTemplate.queryForMap("SELECT * from " + table + " where id=" + id);
  }

  @Then("Row contains such fields headers $fields")
  public void rowContaisSuchFieldsHeader(@Named("fields") ExamplesTable fields) {
    assertThat(
            fields.getHeaders().stream()
                .allMatch(p -> row.keySet().stream().anyMatch(f -> f.equalsIgnoreCase(p))))
        .isTrue();
  }

  @Then("Row not contains such fields headers $fields")
  public void rowNotContaisSuchFieldsHeader(@Named("fields") ExamplesTable fields) {
    assertThat(
            fields.getHeaders().stream()
                .anyMatch(p -> row.keySet().stream().anyMatch(f -> f.equalsIgnoreCase(p))))
        .isFalse();
  }

  @Then("Row contains any of such fields headers $fields")
  public void rowNotContaisAnyOfSuchFieldsHeader(@Named("fields") ExamplesTable fields) {
    assertThat(
            fields.getHeaders().stream()
                .anyMatch(p -> row.keySet().stream().anyMatch(f -> f.equalsIgnoreCase(p))))
        .isTrue();
  }

  @Then("Row field: $field has such value: $value")
  public void rowFieldHasSuchValue(@Named("field") String field, @Named("value") String value) {
    assertThat(
            row.get(row.keySet().stream().filter(k -> k.equalsIgnoreCase(field)).findFirst().get())
                .toString())
        .isEqualTo(value);
  }

  @Then("Row field: $field not set (has null-value)")
  public void rowFieldHasNullValue(@Named("field") String field) {
    assertThat(
            row.get(row.keySet().stream().filter(k -> k.equalsIgnoreCase(field)).findFirst().get()))
        .isNull();
  }

  @When("Get first row of table $table where $keyValue")
  public void GetRowsBySuchParams(
      @Named("table") String table, @Named("keyValue") ExamplesTable keyValue) {
    row = getTableRowsByWhereCondition(table, keyValue).get(0);
  }

  private List<Map<String, Object>> getTableRowsByWhereCondition(
      String table, ExamplesTable keyValue) {
    return this.jdbcTemplate.queryForList(
        "SELECT * from "
            + table
            + " where "
            + keyValue.getRows().get(0).entrySet().stream()
                .map(e -> e.getKey() + "='" + e.getValue() + "'")
                .reduce((a, b) -> a + " and " + b)
                .get());
  }
}
