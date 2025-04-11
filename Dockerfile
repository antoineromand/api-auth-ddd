FROM eclipse-temurin:23-jre
WORKDIR /app
ARG JAR_VERSION
COPY build/libs/AuthProject-${JAR_VERSION}.jar /app/auth.jar
EXPOSE 3008
CMD ["java", "-jar", "/app/auth.jar"]