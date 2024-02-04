FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app/api-teamaker

COPY ./.mvn/ .mvn
COPY ./api-teamaker/mvnw ./
COPY ./api-teamaker/pom.xml ./

RUN chmod +x ./mvnw && ./mvnw dependency:go-offline

COPY ./api-teamaker/src ./src

# Pour le développement
CMD ["./mvnw", "spring-boot:run"]
# Pour la production, décommenter la ligne suivante et commenter celle du dessus
# CMD ["java", "-jar", "target/mon-application.jar"]
