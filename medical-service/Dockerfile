FROM amazoncorretto:21

WORKDIR /app

COPY target/medical-service-*.jar medical-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "medical-service.jar"]