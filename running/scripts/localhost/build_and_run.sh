#!/usr/bin/env bash
echo "=================================================================="
echo " Build project and run spring-boot:run services                   "
echo " (to STOP application kill process by ports, e.g 8080, 8081)      "
echo "=================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean verify -f ${PROJECT_PATH}
mvn spring-boot:run -f ${PROJECT_PATH}/intro-service &
mvn spring-boot:run -f ${PROJECT_PATH}/mainside &
