/*All User's are stored in APP_USER table*/
create table App_User (
  ID INT NOT NULL,
  sso_id VARCHAR(30) NOT NULL,
  password VARCHAR(100) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  state VARCHAR(30) NOT NULL,
  CONSTRAINT APP_USER_PK PRIMARY KEY (ID)
  ENABLE
);

/* Populate APP_USER Table */
INSERT INTO App_User VALUES (1, 'bill', 'abc123', 'Bill', 'Watcher', 'bill@xyz.com', 'Active');
INSERT INTO App_User VALUES (2, 'danny', 'abc124', 'Danny', 'Theys', 'danny@xyz.com', 'Active');
INSERT INTO App_User VALUES (3, 'sam', 'abc125', 'Sam', 'Smith', 'samy@xyz.com', 'Active');
INSERT INTO App_User VALUES (4, 'nicole', 'abc126', 'Nicole', 'warner', 'nicloe@xyz.com', 'Active');
INSERT INTO App_User VALUES (5, 'kenny', 'abc127', 'Kenny', 'Roger', 'kenny@xyz.com', 'Active');
INSERT INTO App_User VALUES (6, 'sudo', 'nimda', 'SUDO', 'SUDO', 'sudo@xxx.xxx', 'Active');
INSERT INTO App_User VALUES (7, 'tsuser', 'tspass1', 'Testside', 'User', 'tsuser@xxx.xxx', 'Active');
INSERT INTO App_User VALUES (8, 'admin', 'nimda', 'Admin', 'User', 'admin@xxx.xxx', 'Active');

INSERT INTO SEQUENCES VALUES('APP_USER_SEQUENCE', 9);