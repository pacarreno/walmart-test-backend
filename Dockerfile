# Docker multi-stage build
 
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE $PORT
ENTRYPOINT ["java","-jar", "-Dserver.port=$PORT","/app.jar"]
