Feature: tables row counts

Narrative:
As a user
I want to check row count of diffrent tables
So that I can check correctness of DB

GivenStories: stories/dbtables/init_and_check_db_pre_condition.story

Scenario: Check row counts for different tables

When Get row count of table [table]
Then Row count of table is [rowCount]

Examples:
|table                 |rowCount              |
|APP_USER              |8                     |
|APP_USER_USER_PROFILE |11                    |
|BANK                  |5                     |
|CONTACT               |8                     |
|CREDIT_CARD           |8                     |
|CUSTOMER              |3                     |
|ITEM                  |5                     |
|ORDERING              |5                     |
|RECHARGE              |3                     |
|SEQUENCES             |7                     |
|USER_PROFILE          |3                     |
|WORKER                |5                     |
