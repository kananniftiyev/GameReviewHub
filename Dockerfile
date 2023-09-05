# Use an official Maven runtime as a parent image
FROM maven:3.9.3-eclipse-temurin-17-focal

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml .

# Build the application
RUN mvn clean install -DskipTests

# Copy the compiled JAR file into the container
COPY target/*.jar GameReviewHub-docker.jar

# Entry point to run the application
CMD ["java", "-jar", "GameReviewHub-docker.jar"]
