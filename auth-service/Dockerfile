# Usa la imagen oficial de Amazon Corretto 21
FROM amazoncorretto:21

WORKDIR /app

COPY target/auth-service-*.jar auth-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "auth-service.jar"]