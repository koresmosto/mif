Feature: tables row content

Narrative:
As a user
I want to check row content of diffrent tables
So that I can check correctness of DB

GivenStories: stories/dbtables/init_and_check_db_pre_condition.story

Scenario: Check row content for table: BANK

When Get row of table BANK with 3
Then Row contains such fields headers |id|name|
And Row not contains such fields headers |owner|any|
And Row contains any of such fields headers |owner|tester|name|id5|
And Row field: name has such value: PrivatBank

Scenario: Check row content for table: APP_USER

When Get row of table APP_USER with 5
Then Row contains such fields headers |id|SSO_ID|password|email|State|
And Row not contains such fields headers |password2|firstname|
And Row field: sso_id has such value: kenny
And Row field: password has such value: abc127
And Row field: email has such value: kenny@xyz.com
And Row field: state has such value: Inactive

Scenario: Check row content for table: CONTACT

When Get row of table CONTACT with 8
Then Row contains such fields headers |first_name|middle_name|last_name|phone_number|birth_day|
And Row not contains such fields headers |second_name|OTHER_NAME|
And Row field: FIRST_NAME has such value: Admin
And Row field: LAST_NAME has such value: Userino
And Row field: PHONE_NUMBER has such value: 0725557763
And Row field: birth_day has such value: 1980-08-29 00:00:00.0
And Row field: middle_name not set (has null-value)

Scenario: Check row content for table: APP_USER_USER_PROFILE

When Get first row of table APP_USER_USER_PROFILE where |USER_ID|USER_PROFILE_ID|
                                                        |6      |3              |
Then Row contains such fields headers |user_id|user_profile_id|
And Row not contains such fields headers |other_id|
And Row field: user_profile_id has such value: 3
And Row field: USER_ID has such value: 6

Scenario: Check row content for table: CREDIT_CARD

When Get row of table CREDIT_CARD with 4
Then Row contains such fields headers |number|type|bank_id|bank_id|contact_id|
And Row not contains such fields headers |order_id|
And Row field: NUMBER has such value: 1123729594138004
And Row field: TYPE has such value: MasterCard
And Row field: bank_id has such value: 4
And Row field: CONTACT_id has such value: 4
