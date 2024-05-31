FROM openjdk:17
WORKDIR /app
COPY target/Odooraa-0.0.1-SNAPSHOT.jar /app
EXPOSE 8083
CMD ["java", "-jar", "Odooraa-0.0.1-SNAPSHOT.jar"]
