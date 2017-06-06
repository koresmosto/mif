/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE App_User_User_Profile (
  user_id         INT NOT NULL,
  user_profile_id INT NOT NULL,
  PRIMARY KEY (user_id, user_profile_id),
  CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES App_User (ID),
  CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES User_Profile (ID)
);

/* Populate JOIN Table */
INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM App_User u, User_Profile p
  WHERE u.sso_id = 'bill' AND p.type = 'USER';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'danny' AND p.type = 'USER';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'sam' AND p.type = 'ADMIN';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'nicole' AND p.type = 'DBA';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'kenny' AND p.type = 'ADMIN';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'kenny' AND p.type = 'DBA';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'sudo' AND p.type = 'USER';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'sudo' AND p.type = 'ADMIN';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'sudo' AND p.type = 'DBA';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'tsuser' AND p.type = 'USER';

INSERT INTO App_User_User_Profile (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'admin' AND p.type = 'ADMIN';
