FROM ubuntu:latest

# Use a multi-stage build to build the application
FROM maven:latest AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files into the container
COPY . .

# Build the Maven project
RUN mvn package

# Create the final runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory in the runtime image
WORKDIR /app

# Copy the built JAR file from the builder stage into the runtime image
COPY --from=builder /app/target/app-1.jar app.jar

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
