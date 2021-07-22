# Github login api
Recruitment project

## Requirements
- Java 11+

## Build and running instructions
Make sure your JAVA_HOME enviroment property is set to proper java version, then run the following script:

`mvnw clean install && java -jar target\login-api-0.0.1-SNAPSHOT.jar`

## Usage
The application supports the following GET request:

`GET http://localhost:8080/users/{login}`