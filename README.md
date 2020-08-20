Project of Social Network for workers
=====================================

Portal will be used by:

*   People and group of people: builders, repairing workers
*   People needed building, repairing and similar type of work

Also there'll be a possibility to order, estimate workers and find job

Usage 
-----
See project ```WIKI```

Technical configs
-----
Read `run/IDE/README.txt` and apply its recommendations to IDE 

<!-- TODO: should be written well (write wiki)-->
Temp notes:
- Start mongodb locally: 
`$> sudo mongod --config /etc/mongodb.conf`
- When init project first with docker elastic:
`$> sudo ./run/elastic/elastic.sh`
- Generate site documentation for project:
`$> mvn clean site --pl mainside,mq-publish,mq-consume,intro-service,cache-service && mvn clean site --pl util`

Mysql migration:
1) go to mainside
2) m clean compile flyway:clean  flyway:migrate --pl mainside

Make release (based on util module):
1) `$>m clean install source:jar javadoc:jar -Prelease`
2) `$>gpg --verify target/*.pom.asc`
3) `$>m deploy -Prelease`
4) go to `https://oss.sonatype.org/#nexus-search;quick~makeitfine`
