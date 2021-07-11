# abstraction-app-webservice\
#TODO - add read-me to each directory - purpose and content of the directory
#take the word AbstractionAppInitializer - DedupInitializer
# abstract the initial app initializer into a file (description file) for Dedup
# split AbstractionAppInitializer into a generic class to support all possible user application not just dedup

#the frontend task graph populates the backend when the app runs for the first time


**# Running the WebService and MariaDB on Docker**\
Step 1- pull mariaDb\
docker pull mariadb/server:10.3\
 
Step 2- run mariaDb\
docker run -d -p 3306:3306 --name mariadb-standalone -e MARIADB_ROOT_PASSWORD=r@@t -e MARIADB_DATABASE=abstraction-app mariadb/server:10.3

Step 3- build web service
docker build -t abstraction-app .
