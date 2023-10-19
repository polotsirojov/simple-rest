FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./target/testing-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]