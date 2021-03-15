FROM openjdk:15-jdk-alpine
ARG JAR_FILE=out/artifacts/webservice_abstraction_app_jar/*.jar
#COPY ${JAR_FILE} app.jar
ADD ${JAR_FILE} app.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","/app.jar"]
#ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]