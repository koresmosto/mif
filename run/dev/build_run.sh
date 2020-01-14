#!/usr/bin/env bash
echo "======================================================================"
echo " Build project and run localhost/docker PARAMETRIZED                  "
echo " For help enter --help                                                "
echo "======================================================================"

#Default script values:
skipTests=false
debug=false
docker=false
mongoMigration=false
buildOnly=false
runOnly=false

PROJECT_PATH="`dirname \"$0\"`"/../..

DskipTests=""
Pdebug=""
docker_compose_file="${PROJECT_PATH}/docker-compose.yml"
installOrVerify="verify"

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
            docker_compose_file="${docker_compose_file} -f ${PROJECT_PATH}/docker-compose-debug.yml"
            ;;
      "do" | "docker")
            docker=true
            installOrVerify="install"
            Pdocker="-Pdocker"
            ;;
      "mm" | "mongoMigrate")
            mongoMigration=true
            ;;
      "b" | "buildOnly" | "build")
            buildOnly=true
            ;;
      "r" | "runOnly" | "run")
            runOnly=true
            ;;
      "h" | "help" | "--h" | "--help")
            echo "Script for build and run project locally and in docker"
            echo "params:"
            echo "  s  - skipTests"
            echo "  d  - debug enable"
            echo "  do - docker enable"
            echo "  mm - mongodb migration scripts (parametrize only locally 'dev')"
            echo "  b  - build only"
            echo "  r  - run only"
            exit
            ;;
      *)
    esac
done

echo "Script running>> docker: ${docker} | skipTests: ${skipTests} | debug: ${debug} \
| mongoMigration: ${mongoMigration} | runOnly: ${runOnly} | buildOnly: ${buildOnly}"

if ${mongoMigration} && ! ${docker} ; then
  sh $(dirname "$0")/mongo_migrate.sh
fi;

if ! ${runOnly} ; then
  mvn clean ${installOrVerify} ${DskipTests} ${Pdocker} -f ${PROJECT_PATH}
fi;
#If maven commands failed exit the script
if [[ "$?" -ne 0 ]] ; then
  exit $rc
fi

if ! ${buildOnly} ; then
  if ${docker} ; then
    docker-compose -f ${docker_compose_file} up
  else
    (trap 'kill 0' SIGINT;
    mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/intro-service &
    mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mq-publish &
    mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mq-consume &
    mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mainside)
  fi;
fi;
