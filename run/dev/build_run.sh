#!/usr/bin/env bash
echo "======================================================================"
echo " Build project and run localhost/docker PARAMETRIZED                  "
echo " Run in docker(do) debug(d) skipTest(s), e.g.: br.sh s d do | br.sh s "
echo "======================================================================"

#Default script values:
skipTests=false
debug=false
docker=false
mongoMigration=false

DskipTests=""
Pdebug=""
docker_compose_file="docker-compose.yml"
DmongodbMigrationActive=""

for arg in "$@"
do
    case "$arg" in
      "s" | "skip" | "skipTests" | "skiptest" | "skipTest")
            skipTests=true
            DskipTests="-DskipTests=true"
            ;;
      "d" | "debug")
            debug=true
            Pdebug="-Pdebug"
            docker_compose_file="docker-compose-debug.yml"
            ;;
      "do" | "docker")
            docker=true
            Pdocker="-Pdocker"
            ;;
      "mm" | "mongoMigrate")
            mongoMigration=true
            DmongodbMigrationActive="-Dmongodb.migration.active=true"
            ;;
      *)
    esac
done

echo "Script running>> docker: ${docker} | skipTests: ${skipTests} | debug: ${debug} | mongoMigration: ${mongoMigration}"

PROJECT_PATH="`dirname \"$0\"`"/../..

mvn clean install ${DskipTests} ${Pdocker} -f ${PROJECT_PATH}
if ${docker} ; then
  docker-compose -f ${PROJECT_PATH}/${docker_compose_file} up
else
  mvn spring-boot:run ${Pdebug} ${DmongodbMigrationActive} -f ${PROJECT_PATH}/intro-service | mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mainside
fi;
