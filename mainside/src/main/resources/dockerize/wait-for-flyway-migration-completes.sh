#!/bin/sh
while true;
do
  if nslookup flyway || ! nslookup intro-service;
    then echo \"Waiting depends_on services: flyway, intro-service ...\";
    sleep 2;
  else
    echo \"Depends_on servcies read!\";
    break; fi;
done;
