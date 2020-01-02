#!/bin/sh
while true;
do
  if nslookup flyway;
    then echo \"FLYWAY migration in progress ...\";
    sleep 2;
  else
    echo \"FLYWAY migration finished!\";
    break; fi;
done;
