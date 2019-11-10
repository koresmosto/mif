/* USER_PROFILE table contains all possible roles */
CREATE TABLE USER_PROFILE (
  ID INT NOT NULL,
  type VARCHAR(30) NOT NULL,
  CONSTRAINT USER_PROFILE_PK PRIMARY KEY (ID)
  ENABLE
);

CREATE TABLE SEQUENCES (
  SEQ_NAME VARCHAR(80) NOT NULL,
  SEQ_NUMBER INT NOT NULL,
  CONSTRAINT SEQUENCES_PK PRIMARY KEY (SEQ_NAME)
  ENABLE
);

INSERT INTO USER_PROFILE VALUES (1, 'USER');
INSERT INTO USER_PROFILE VALUES (2, 'ADMIN');
INSERT INTO USER_PROFILE VALUES (3, 'DBA');

--  Should be inserted next generated value (4)
INSERT INTO SEQUENCES VALUES('USER_PROFILE_SEQUENCE', 4);