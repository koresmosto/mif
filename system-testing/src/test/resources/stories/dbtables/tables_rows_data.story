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

Scenario: Check row content for table: CUSTOMER

When Get row of table CUSTOMER with 7
Then Row contains such fields headers |ID|
And Row not contains such fields headers |order_id|

Scenario: Check row content for table: WORKER

When Get row of table WORKER with 8
Then Row contains such fields headers |ID|
And Row not contains such fields headers |order_id|

Scenario: Check row content for table: ITEM

When Get row of table ITEM with 5
Then Row contains such fields headers |header|price|
And Row not contains such fields headers |type|city|
And Row field: header has such value: Install plumber
And Row field: price has such value: 40.0

Scenario: Check row content for table: ORDERING

When Get row of table ORDERING with 2
Then Row contains such fields headers |description|status|item_id|credit_card_id|
And Row not contains such fields headers |type|other|
And Row field: description has such value: Deliver to Myshkova, Stynova 15/15 after 18:00
And Row field: status has such value: Pending
And Row field: item_id has such value: 2
And Row field: credit_card_id has such value: 7

Scenario: Check row content for table: RECHARGE

When Get row of table RECHARGE with 3
Then Row contains such fields headers |amount|payment_type|
And Row not contains such fields headers |order|
And Row field: amount has such value: 1234.0
And Row field: payment_type has such value: Card

Scenario: Check row content for table: SEQUENCES

When Get first row of table SEQUENCES where |seq_name           |
                                            |RECHARGE_SEQUENCE  |
Then Row contains such fields headers |seq_number|
And Row not contains such fields headers |order|
And Row field: seq_number has such value: 4

Scenario: Check row content for table: USER_PROFILE

When Get row of table USER_PROFILE with 3
Then Row contains such fields headers |Type|
And Row not contains such fields headers |order_id|
And Row field: TYPE has such value: DBA
