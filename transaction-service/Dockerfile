FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/transaction-service-0.0.1-SNAPSHOT.jar transaction-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
