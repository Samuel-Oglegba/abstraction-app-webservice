FROM openjdk:15-jdk-alpine
EXPOSE 8085
WORKDIR /app
COPY target/webservice-abstraction-app-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "webservice-abstraction-app-0.0.1-SNAPSHOT.jar" ]
