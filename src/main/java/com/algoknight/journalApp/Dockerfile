# Use official Java image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render uses PORT env)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/journalApp-0.0.1-SNAPSHOT.jar"]