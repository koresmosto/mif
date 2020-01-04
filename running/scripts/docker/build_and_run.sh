#!/usr/bin/env bash
echo "=================================================================="
echo " Build project and run                                            "
echo "=================================================================="

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean install -Pdocker -f ${PROJECT_PATH}
docker-compose -f ${PROJECT_PATH}/docker-compose.yml up