#!/usr/bin/env bash
echo "=================================================================="
echo " Build project and run spring-boot:run services                   "
echo " (to stop application kill process by ports, e.g 8080, 8081)      "
echo "=================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean verify -f ${PROJECT_PATH}
mvn spring-boot:run -f ${PROJECT_PATH}/intro-service &
# Empiric waiting until intro-service stared (used by mainside)
# todo: think of deleting this waiting (it could be redundant)
sleep 7s;
mvn spring-boot:run -f ${PROJECT_PATH}/mainside &
