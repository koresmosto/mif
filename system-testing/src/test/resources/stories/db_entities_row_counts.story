Feature: row counts of entites

Narrative:
As a user
I want to check row count of entities
So that I can check correctness of DB

Scenario: check Bank
Given JdbcTemplate instance
When Call for row count of entity BANK
Then Row count of entity is 5

Scenario: check CreditCard
Given JdbcTemplate instance
When Call for row count of entity CREDIT_CARD
Then Row count of entity is 8