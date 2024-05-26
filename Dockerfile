# Start with a base image containing Java runtime
FROM openjdk:17

# The application's jar file
ARG JAR_FILE=target/Odooraa-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} Odooraa.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-jar","/Odooraa.jar"]
