#!/usr/bin/env bash
echo "=================================================================="
echo " Prepare elasticstack directory sub-dirs and files for Elastic    "
echo "=================================================================="

SCRIPT_PATH="`dirname \"$0\"`"
PROJECT_PATH=${SCRIPT_PATH}/../..
mkdir ${PROJECT_PATH}/elasticstack
ELS=${PROJECT_PATH}/elasticstack

mkdir ${ELS}/elasticsearch
mkdir ${ELS}/elasticsearch/data
chmod 777 -R ${ELS}/elasticsearch
touch ${ELS}/elasticsearch/data/empty

mkdir ${ELS}/filebeat
mkdir ${ELS}/filebeat/data
chmod 777 -R ${ELS}/filebeat
touch ${ELS}/filebeat/data/empty
cp ${SCRIPT_PATH}/resources/filebeat.docker.template.yml ${ELS}/filebeat/filebeat.docker.yml
sudo chown root ${ELS}/filebeat/filebeat.docker.yml
sudo chmod 600 ${ELS}/filebeat/filebeat.docker.yml

mkdir ${ELS}/logstash
mkdir ${ELS}/logstash/pipeline
cp ${SCRIPT_PATH}/resources/logstash.template.conf ${ELS}/logstash/pipeline/logstash.conf
chmod 777 -R ${ELS}/logstash
