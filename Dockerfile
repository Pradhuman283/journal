# --- Stage 1: Build ---
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy the project files
COPY . .

# Ensure mvnw has execution permissions
RUN chmod +x mvnw

# Build the application (skipping tests for speed in image creation)
RUN ./mvnw clean package -DskipTests

# --- Stage 2: Runtime ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/journalApp-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Environment variables should be passed at runtime using -e or .env file
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
