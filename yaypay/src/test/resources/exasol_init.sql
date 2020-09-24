/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */
CREATE SCHEMA my_schema;
OPEN SCHEMA MY_SCHEMA;
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

ALTER TABLE MY_SCHEMA.BIZ ADD CONSTRAINT TEST_1323850274965437612980224 PRIMARY KEY ("ID")  ENABLE ;

INSERT INTO BIZ (ID,NAME,DEFAULT_USER_AR_ID,ADDRESS_ID,TAX_NUMBER,SETTINGS,API_KEY,IS_DEMO,INTEGRATIONS,CREATED_AT,
                 LOGO_ID,SETTINGS_INBOUND_HOST,DEFAULT_CURRENCY,VERSION,UPDATED_AT,IS_AUTOTEST,DEF_CC_ACC_ID,
                 DEF_ACH_ACC_ID,USECALCULATEDCUSTOMERBALANCE,SWITCHTOMULTICURRENCYDAILYEXCHANGERATES,BIZ_URL,
                 DEF_UNDEP_ACC,REFERENCE_NAME)
     VALUES
(1,'My Company LLC',0,1,'123456789',NULL,'378355ad4d318d1f7ceecec1a52d3d7d',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,
 NULL,'false','false',NULL,NULL,NULL),
(2,'NotMyBiz',0,2,NULL,NULL,'120c289d362f914078611ac0075c99c2',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,
 NULL,NULL,NULL),
(3,'Other biz',0,3,NULL,NULL,'82a872f8d7996b4d9944c09c950ed602',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(4,'FOP',0,4,NULL,NULL,'d8e85d5d51a2fe3bf6bba23e0a045ea5',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(5,'His biz',0,5,NULL,NULL,'4d4289c1824f9d0dd6d98a8b9b6510b2',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(6,'Kalinka OOO',0,6,NULL,NULL,'1a2cbe6ce9c4ea950219b1e5e71b8077',false,NULL,'2017-11-11 08:46:41.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(7,'Initial autotest biz',0,7,NULL,NULL,NULL,false,NULL,'2017-11-11 08:52:38.000',0,NULL,'USD',0,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(8,'Yaypay',0,10002,NULL,NULL,NULL,true,'{"bizId": 0, "paymentCredentials": {"apsCredentials": {"apiKey": "zOU7+XQ8tTZIeGFwQ+TepGvld743QF8RnkYpnpVZpdHe0+8wP6HK7t9ssKgZ9FyAaQn7yA+gk6bCDtZuhZTdvQ==", "status": "ACTIVE", "secCode": "CCD", "password": "9uXeP7EN5PXcC2S4mrnqEyabHGI6BsC2e1ZB8zwXpAEvk1d73YEsdw==", "queryURL": "https://secure.apspaymentgateway.com/api/query.php", "testMode": true, "userName": "apsmulticurrency", "gatewayURL": "https://secure.apspaymentgateway.com/api/v2/three-step"}}, "netSuiteCredentials": {"testMode": false, "useNewWay": false, "uploadNote": false, "useService": false, "customFields": {"invoiceCustomFields": [{"erpName": "term_name", "systemName": "term_name", "displayName": "Term name", "configurable": true, "displayOnPDF": false, "displayOnStatementPage": true, "displayOnCustomerPortal": true, "enableSyncInvoiceItemFieldName": false}, {"erpName": "sales_terms", "systemName": "sales_terms", "displayName": "Sales terms", "configurable": true, "displayOnPDF": false, "displayOnStatementPage": true, "displayOnCustomerPortal": true, "enableSyncInvoiceItemFieldName": false}, {"erpName": "ship_method", "systemName": "ship_method", "displayName": "Ship method", "configurable": true, "displayOnPDF": false, "displayOnStatementPage": true, "displayOnCustomerPortal": true, "enableSyncInvoiceItemFieldName": false}], "customerCustomFields": [{"erpName": "erp_Customer_field1", "systemName": "Customer_Group", "displayName": "Customer Group", "configurable": true, "displayOnPDF": false, "filterOnAgingReport": true, "displayOnStatementPage": true, "enableSyncInvoiceItemFieldName": false}, {"erpName": "erp_Customer_field3", "systemName": "Customer_Type", "displayName": "Customer Type", "configurable": true, "displayOnPDF": false, "filterOnAgingReport": true, "displayOnStatementPage": true, "enableSyncInvoiceItemFieldName": false}, {"erpName": "erp_Customer_field2", "systemName": "Plan_ID", "displayName": "Plan ID", "configurable": true, "displayOnPDF": false, "filterOnAgingReport": true, "displayOnStatementPage": true, "enableSyncInvoiceItemFieldName": false}]}, "downloadNote": false, "multiCurrency": false, "useEmailField": false, "createPayments": false, "newAdjustmentIds": false, "onlyOpenEntities": false, "autoCreateContact": false, "useFxBalanceField": false, "syncAllContactsAsBilling": false}, "creditSafeCredentials": {"url": "http://172.31.42.205:8086/GlobalData/1.3/MainServiceBasic.svc", "user": "yaypayLive", "password": "V10xFH6Z8bJbYEVbp22tymSnuQGozcQMCF9mZyXeQvalaWCvFbXoxIW+/w=="}}','2020-08-28 12:54:39.000',0,NULL,'USD',0,NULL,false,NULL,NULL,'false','false',NULL,NULL,NULL);
