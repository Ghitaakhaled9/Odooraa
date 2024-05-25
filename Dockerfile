FROM openjdk:17
EXPOSE 8083
WORKDIR /app
CMD ["sh", "/app/script.sh"]