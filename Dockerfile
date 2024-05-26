FROM openjdk:17
EXPOSE 8083
ADD target/Odooraa-0.0.1-SNAPSHOT.jar Odooraa.jar
ENTRYPOINT ["java", "-jar", "Odooraa.jar"]

