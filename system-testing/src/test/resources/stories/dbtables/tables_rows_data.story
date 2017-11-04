Feature: tables row counts

Narrative:
As a user
I want to check row content of diffrent tables
So that I can check correctness of DB

Scenario: Check row content for table: BANK

Given JdbcTemplate instance
When Get row of table BANK with 3
Then Row contains such fields headers |id|name|
And Row not contains such fields headers |owner|any|
And Row contains any of such fields headers |owner|tester|name|id5|
And Row field: name has such value PrivatBank
