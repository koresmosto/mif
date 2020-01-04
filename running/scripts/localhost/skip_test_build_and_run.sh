#!/usr/bin/env bash
echo "=================================================================="
echo " Build project and run spring-boot:run services (SKIP TESTS)      "
echo "=================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean verify -DskipTests=true -f ${PROJECT_PATH}
mvn spring-boot:run -f ${PROJECT_PATH}/intro-service &
mvn spring-boot:run -f ${PROJECT_PATH}/mainside &
