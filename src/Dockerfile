#FROM ubuntu:latest AS build
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#RUN apt-get install maven -y
#COPY . .
#RUN mvn package --no-daemon
# Use an OpenJDK base image with Maven pre-installed
FROM maven:latest AS build

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the Maven project
RUN mvn package --no-daemon

# Switch to a lighter image for the runtime environment
FROM openjdk:17-jdk-slim

# Expose port 8080 (assuming your Spring Boot application runs on this port)
EXPOSE 8050

# Copy the built JAR file from the build stage into the runtime image
COPY --from=target /target/demo-1.jar app.jar

# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]
