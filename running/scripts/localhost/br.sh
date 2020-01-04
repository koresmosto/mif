#!/usr/bin/env bash
echo "===================================================================="
echo " Build project and run spring-boot:run services PARAMETRIZED        "
echo " Run in debug (d) skipTest (s), e.g.: br.sh s d | br.sh s | br.sh d "
echo " (to stop application kill process by ports, e.g 8080, 8081)        "
echo "===================================================================="

DskipTests=""
Pdebug=""

for arg in "$@"
do
    case "$arg" in
      "s")    DskipTests="-DskipTests=true"
              ;;
      "d")    Pdebug="-Pdebug"
              ;;
      *)
    esac
done

echo "Script running>> ${DskipTests} ${Pdebug}"

PROJECT_PATH="`dirname \"$0\"`"/../../..

mvn clean verify ${DskipTests} -f ${PROJECT_PATH}
mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/intro-service &
mvn spring-boot:run ${Pdebug} -f ${PROJECT_PATH}/mainside &
