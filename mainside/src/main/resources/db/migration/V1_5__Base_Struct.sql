CREATE TABLE Question (
    ID INT NOT NULL,
    question VARCHAR(500) NOT NULL,
    variant1 VARCHAR(100) NOT NULL,
    variant2 VARCHAR(100) NOT NULL,
    variant3 VARCHAR(100) NOT NULL,
    variant4 VARCHAR(100) NOT NULL,
    answer INT NOT NULL,
    CONSTRAINT QUESTION_PK PRIMARY KEY (ID)
    ENABLE
);

declare
    question_first_gen_id INTEGER;
begin
   select max(id)
   into question_first_gen_id
   from QUESTION;
   IF question_first_gen_id is null THEN
     question_first_gen_id := 1  ;
   ELSE
     question_first_gen_id := question_first_gen_id + 1;
   END IF;

   execute immediate 'Create sequence question_id_seq start with '
                      || question_first_gen_id || ' increment by 1';
end;
