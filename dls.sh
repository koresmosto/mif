#!/bin/bash
#
# Default linux script for project
mvn clean compile flyway:clean flyway:migrate -pl mainside
