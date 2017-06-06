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

declare
  app_user_first_gen_id INTEGER;
  user_profile_first_gen_id INTEGER;
begin
  select max(id)
  into app_user_first_gen_id
  from App_User;
  IF app_user_first_gen_id is null THEN
    app_user_first_gen_id := 1  ;
  ELSE
    app_user_first_gen_id := app_user_first_gen_id + 1;
  END IF;

  execute immediate 'Create sequence app_user_id_seq start with '
                    || app_user_first_gen_id || ' increment by 1';
end;
