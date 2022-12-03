FROM maven:3.6.3-jdk-11 AS build-project
ADD . ./docker-spring-boot
WORKDIR /docker-spring-boot
RUN mvn clean install


FROM openjdk:11.0.6-jre
EXPOSE 8080

COPY --from=build-project /docker-spring-boot/target/blog-api-docker.jar ./docker-spring-boot.jar
CMD ["java", "-jar", "docker-spring-boot.jar"]