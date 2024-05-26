FROM openjdk:17
EXPOSE 8083
ADD target/Odooraa.jar Odooraa.jar
ENTRYPOINT ["java","-jar","Odooraa-0.0.1-SNAPSHOT.jar"]
