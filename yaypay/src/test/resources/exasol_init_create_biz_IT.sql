/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */
CREATE SCHEMA EXASOL_YAYPAY_TEST;
OPEN SCHEMA EXASOL_YAYPAY_TEST;
CREATE TABLE "BIZ" (
                       "ID" DECIMAL(10,0) NOT NULL ,
                       "NAME" VARCHAR(128) UTF8 NOT NULL ,
                       "DEFAULT_USER_AR_ID" DECIMAL(10,0) ,
                       "ADDRESS_ID" DECIMAL(10,0) ,
                       "TAX_NUMBER" VARCHAR(32) UTF8 ,
                       "SETTINGS" VARCHAR(2000000) UTF8 ,
                       "API_KEY" VARCHAR(128) UTF8 ,
                       "IS_DEMO" BOOLEAN ,
                       "INTEGRATIONS" VARCHAR(2000000) UTF8 ,
                       "CREATED_AT" TIMESTAMP ,
                       "LOGO_ID" DECIMAL(10,0) ,
                       "SETTINGS_INBOUND_HOST" VARCHAR(64) UTF8 ,
                       "DEFAULT_CURRENCY" VARCHAR(128) UTF8 ,
                       "VERSION" DECIMAL(20,0) ,
                       "UPDATED_AT" TIMESTAMP ,
                       "IS_AUTOTEST" BOOLEAN ,
                       "DEF_CC_ACC_ID" DECIMAL(10,0) ,
                       "DEF_ACH_ACC_ID" DECIMAL(10,0) ,
                       "USECALCULATEDCUSTOMERBALANCE" VARCHAR(5) UTF8 ,
                       "SWITCHTOMULTICURRENCYDAILYEXCHANGERATES" VARCHAR(5) UTF8 ,
                       "BIZ_URL" VARCHAR(128) UTF8 ,
                       "DEF_UNDEP_ACC" VARCHAR(30) UTF8 ,
                       "REFERENCE_NAME" VARCHAR(128) UTF8
);

ALTER TABLE EXASOL_YAYPAY_TEST.BIZ ADD CONSTRAINT TEST_1323850274965437612980224 PRIMARY KEY ("ID")  ENABLE ;
