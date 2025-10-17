# Step 1: Use Maven to build the project
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies first (caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code
COPY src ./src

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Step 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port if your app runs on a specific port (e.g., 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
