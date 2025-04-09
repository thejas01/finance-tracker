FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/budget-service-0.0.1-SNAPSHOT.jar budget-service.jar
EXPOSE 8881
ENTRYPOINT ["java", "-jar", "budget-service.jar"]
