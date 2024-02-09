#FROM openjdk:11-jdk-slim
#
#ARG JAR_FILE=target/*.jar
#
#
#COPY target/inventory.jar /app/inventory.jar
#
#ENTRYPOINT ["java", "-jar", "inventory.jar"]



FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file from the local file system into the container at the specified path
COPY target/inventory.jar /app/inventory.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "/app/inventory.jar"]