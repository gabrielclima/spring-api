# java-spring-rest
A Java REST application.

# Build & Run
gradle clean build && java -jar build/libs/spring-rest-api-0.1.0.jar

# Testing 
curl -X POST -H "Content-type: application/json" localhost:8080/register -d '{"name":"Gabriel", "email":"teste@teste.com"}'
