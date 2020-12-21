FROM openjdk:11-jre-slim
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} /order-management-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/order-management-0.0.1-SNAPSHOT.jar"]