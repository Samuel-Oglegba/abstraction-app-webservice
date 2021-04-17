FROM openjdk:15-jdk-alpine
<<<<<<< HEAD
EXPOSE 8080
WORKDIR /app
COPY target/webservice-abstraction-app-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "webservice-abstraction-app-0.0.1-SNAPSHOT.jar" ]
=======
ARG JAR_FILE=out/artifacts/webservice_abstraction_app_jar/*.jar
#COPY ${JAR_FILE} app.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8082
EXPOSE 3306
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=test", "-jar", "/app.jar"]
>>>>>>> 7b4894b0de3d659c8a5724e49dca2deace10a8b6
