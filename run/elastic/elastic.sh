#!/usr/bin/env bash
echo "=================================================================="
echo " Prepare elasticstack directory sub-dirs and files for Elastic    "
echo "=================================================================="

SCRIPT_PATH="`dirname \"$0\"`"
PROJECT_PATH=${SCRIPT_PATH}/../..
mkdir ${PROJECT_PATH}/elasticstack
ELS=${PROJECT_PATH}/elasticstack
chmod 777 ${ELS}

mkdir ${ELS}/elasticsearch
mkdir ${ELS}/elasticsearch/data
touch ${ELS}/elasticsearch/data/empty
chmod 777 -R ${ELS}/elasticsearch

mkdir ${ELS}/filebeat
mkdir ${ELS}/filebeat/data
touch ${ELS}/filebeat/data/empty
chmod 777 -R ${ELS}/filebeat
cp ${SCRIPT_PATH}/resources/filebeat.docker.template.yml ${ELS}/filebeat/filebeat.docker.yml
chown root ${ELS}/filebeat/filebeat.docker.yml

mkdir ${ELS}/logstash
mkdir ${ELS}/logstash/pipeline
cp ${SCRIPT_PATH}/resources/logstash.template.conf ${ELS}/logstash/pipeline/logstash.conf
chmod 777 -R ${ELS}/logstash
