FROM azul/zulu-openjdk-alpine:11

COPY target/application*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]