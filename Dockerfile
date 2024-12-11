FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar backend.jar

EXPOSE 8080

CMD ["java", "-jar", "backend.jar"]