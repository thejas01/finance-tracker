FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY target/report-service-0.0.1-SNAPSHOT.jar report-service.jar
EXPOSE 8801
ENTRYPOINT ["java", "-jar", "report-service.jar"]
