#!/usr/bin/env bash
echo "=================================================================="
echo " Build project and run (DEBUG)                                    "
echo "=================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean install -DskipTests=true -Pdocker -f ${PROJECT_PATH}
docker-compose -f ${PROJECT_PATH}/docker-compose-debug.yml up