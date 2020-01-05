#!/usr/bin/env bash
echo "===================================================================="
echo " Build project and run docker PARAMETRIZED        "
echo " Run in debug (d) skipTest (s), e.g.: br.sh s d | br.sh s | br.sh d "
echo "===================================================================="

DskipTests=""
docker_compose_file="docker-compose.yml"

for arg in "$@"
do
    case "$arg" in
      "s" | "skip" | "skipTests" | "skiptest" | "skipTest")
            DskipTests="-DskipTests=true"
            ;;
      "d" | "debug")
            docker_compose_file="docker-compose-debug.yml"
            ;;
      *)
    esac
done

echo "Script running>> ${DskipTests} ${docker_compose_file}"

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean install ${DskipTests} -Pdocker -f ${PROJECT_PATH}
docker-compose -f ${PROJECT_PATH}/${docker_compose_file} up
