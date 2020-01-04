#!/usr/bin/env bash
echo "======================================================================="
echo " Build project and run spring-boot:run services (SKIP TESTS and DEBUG) "
echo "======================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..
mvn clean verify -DskipTests=true -f ${PROJECT_PATH}
mvn spring-boot:run -f ${PROJECT_PATH}/intro-service -Pdebug &
mvn spring-boot:run -f ${PROJECT_PATH}/mainside -Pdebug &
