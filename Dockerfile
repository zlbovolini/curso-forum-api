FROM openjdk:11.0.12-jdk-slim

RUN addgroup spring && adduser --ingroup spring spring
USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "/app.jar"]