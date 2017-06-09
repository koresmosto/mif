/* USER_PROFILE table contains all possible roles */
CREATE TABLE User_Profile (
  ID INT NOT NULL,
  type VARCHAR(30) NOT NULL,
  CONSTRAINT USER_PROFILE_PK PRIMARY KEY (ID)
  ENABLE
);

declare
  user_profile_first_gen_id INTEGER;
begin
  select max(id)
  into user_profile_first_gen_id
  from User_Profile;
  IF user_profile_first_gen_id is null THEN
    user_profile_first_gen_id := 1  ;
  ELSE
    user_profile_first_gen_id := user_profile_first_gen_id + 1;
  END IF;

  execute immediate 'Create sequence user_profile_id_seq start with '
                    || user_profile_first_gen_id || ' increment by 1';
end;
