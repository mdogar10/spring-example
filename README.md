# Address Lookup service
This is a address lookup microservice project built using spring boot, spring restFul client and gradle. 
This service lookup an address for the given postcode and returns AddressResponse object.

### The structure of the project is as follows:
* / - Gradle settings, README, etc.
* /src/main - Spring Boot application
* /src/test - Spring Boot application unit/integration test

### Set up and build
Pre-requisites
* Java 8+

The build.gradle uses the Gradle jar plugin to assemble the application and all it's dependencies into a single jar.

#### To run the application:
```
./gradlew clean build
java -jar build/libs/addressLookupService-0.0.1.jar
```
(You can take the jar and run it anywhere where there is Java 8+ JDK. It contains all the dependencies it needs so you don't need to install spring boot on your machine).

#####Visit http://localhost:8080/api/v1/address/postcode/BT48 6DQ

#### To run unit tests:
```
./gradlew test
```

#### api documentation can be found at:
```
api-docs/addressLookupService.yml
```