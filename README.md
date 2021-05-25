# abstraction-app-webservice\

**#Running the WebService and MariaDB on Docker**\
Step 1- pull mariaDb\
docker pull mariadb/server:10.3\
 
Step 2- run mariaDb\
docker run -d -p 3306:3306 --name mariadb-standalone -e MARIADB_ROOT_PASSWORD=r@@t -e MARIADB_DATABASE=abstraction-app mariadb/server:10.3

