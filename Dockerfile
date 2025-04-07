FROM maven:3.9-amazoncorretto-21-alpine  AS  build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/loginRegisterService-0.0.1-SNAPSHOT.jar loginRegisterService.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","loginRegisterService.jar" ]