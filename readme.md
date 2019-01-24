# Spring Boot Temperatures Rest API

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker] (https://www.docker.com)

## Running the application locally

The project comes with a docker-compose.yml file to the stack in containers.
The key thing to remember is the datasource must be available for the Spring Boot Application to build.
Please deploy the db container, build the application, then deploy the full stack.

## Testing

There is a single integration test in TemperatureTestRestClient to test the API. It will run through a full lifecycle of the
temperature entity.