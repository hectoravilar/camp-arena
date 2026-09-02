# Stage 1: Build the Java application using Maven and JDK 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Set the working directory inside the build container
WORKDIR /app

# Copy dependency definition and source files
COPY pom.xml .
COPY src ./src

# Compile and package the application into an executable JAR, skipping unit tests for speed
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image with JRE 21 only
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the runtime container
WORKDIR /opt/app

# Copy the compiled JAR artifact from the builder stage
COPY --from=builder /app/target/game-server-0.0.1-SNAPSHOT.jar /opt/app/game-server.jar

# Document that the application listens on port 8080
EXPOSE 8080

# Configure the container execution entrypoint
ENTRYPOINT ["java", "-jar", "game-server.jar"]