# Use an official Maven image as the build image
FROM maven:3.8.3-openjdk-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use an official OpenJDK 17 image as the base image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build image to the container
COPY --from=builder /app/target/*.jar /app/generic-service-api.jar

# Expose the port on which the Spring Boot application will listen
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "--add-opens=java.base/java.lang=ALL-UNNAMED", "--add-opens=java.base/java.util=ALL-UNNAMED", "-jar", "/app/generic-service-api.jar"]
