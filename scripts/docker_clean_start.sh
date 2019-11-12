#!/usr/bin/env bash
#
# Main docker start script
#
# Run from local machine: pwd && m clean install && c mainside/ && m spring-boot:run
#
echo "=========================================================="
echo " Remove docker containers, create new ones and start them"
echo "=========================================================="
. $(dirname $0)/docker_clean.sh
docker-compose up -d
wait_time=2s
echo "sleep for $wait_time for 'echo 'tail -f /data/log.txt' >> output.sh' executes in container"
sleep $wait_time
docker exec -it j11m bash '/data/output.sh'
