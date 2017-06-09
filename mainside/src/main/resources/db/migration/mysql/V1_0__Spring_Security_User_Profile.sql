/* USER_PROFILE table contains all possible roles */
CREATE TABLE User_Profile (
  `ID` INT NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  CONSTRAINT USER_PROFILE_PK PRIMARY KEY (ID)
);

