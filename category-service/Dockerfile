FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/category-service-0.0.1-SNAPSHOT.jar category-service.jar
EXPOSE 8889
ENTRYPOINT ["java", "-jar", "category-service.jar"]
