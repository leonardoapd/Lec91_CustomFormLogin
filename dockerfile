FROM eclipse-temurin:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Lec91_CustomFormLogin-1.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/Lec91_CustomFormLogin-1.jar"]