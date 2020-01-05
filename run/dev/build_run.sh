#!/usr/bin/env bash
echo "======================================================================"
echo " Build project and run localhost/docker PARAMETRIZED                  "
echo " Run in docker(do) debug(d) skipTest(s), e.g.: br.sh s d do | br.sh s "
echo "======================================================================"

docker=false

DskipTests=""

Pdebug=""
docker_compose_file="docker-compose.yml"

for arg in "$@"
do
    case "$arg" in
      "s" | "skip" | "skipTests" | "skiptest" | "skipTest")
            DskipTests="-DskipTests=true"
            ;;
      "d" | "debug")
            Pdebug="-Pdebug"
            docker_compose_file="docker-compose-debug.yml"
            ;;
      "do" | "docker")
            docker=true
            ;;
      *)
    esac
done

echo "Script running>> ${DskipTests}  ${Pdebug} docker: ${docker}"

PROJECT_PATH="`dirname \"$0\"`"/../..

if ${docker} ; then
  mvn clean install ${DskipTests} -Pdocker -f ${PROJECT_PATH}
  docker-compose -f ${PROJECT_PATH}/${docker_compose_file} up
else
  mvn clean verify ${DskipTests} -f ${PROJECT_PATH}
  mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/intro-service |
  mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mainside
fi;
