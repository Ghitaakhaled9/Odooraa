FROM openjdk:17
EXPOSE 8083
ADD target/Odooraa.jar Odooraa.jar
ENTRYPOINT ["java","-jar","/Odooraa"]