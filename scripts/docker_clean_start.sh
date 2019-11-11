#!/usr/bin/env bash
#
# Main docker start script
#
echo "=========================================================="
echo " Remove docker containers, create new ones and start them"
echo "=========================================================="
echo ""
docker stop mys j11m
docker rm mys j11m
docker network rm makeitfine_mifnet_back
docker-compose up -d
wait_time=10s
echo "sleep for $wait_time while maven repos download executes"
sleep $wait_time
docker exec -it j11m bash '/data/output.sh'
