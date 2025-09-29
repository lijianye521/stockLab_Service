# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4.10 application called **StockLab_Service** using Java 17. The project uses Maven for build management and MyBatis for database integration.

## Key Technologies
- **Spring Boot 3.4.10** - Main application framework
- **MyBatis 3.0.5** - SQL mapping framework with Spring Boot integration
- **Maven** - Build and dependency management
- **JUnit 5** - Testing framework

## Common Commands

### Build and Run
```bash
# Clean and compile the project
./mvnw clean compile

# Package the application
./mvnw clean package

# Run the application
./mvnw spring-boot:run

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Testing
```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report

# Run a specific test class
./mvnw test -Dtest=StockLabServiceApplicationTests

# Run tests in a specific package
./mvnw test -Dtest=com.example.stocklab_service.**
```

### Development
```bash
# Generate sources and resources
./mvnw generate-sources generate-resources

# Validate project structure
./mvnw validate

# Show dependency tree
./mvnw dependency:tree
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/stocklab_service/
│   │   └── StockLabServiceApplication.java    # Main Spring Boot application class
│   └── resources/
│       └── application.properties             # Configuration properties
└── test/
    └── java/com/example/stocklab_service/
        └── StockLabServiceApplicationTests.java # Main test class
```

## Architecture Notes

- **Package Structure**: Uses standard Maven layout with `com.example.stocklab_service` as the base package
- **Database Integration**: MyBatis is configured for SQL mapping - look for mapper interfaces and XML files when adding database functionality
- **Configuration**: Spring Boot auto-configuration is enabled; application properties are in `application.properties`
- **Testing**: Uses Spring Boot Test framework with JUnit 5

## Development Guidelines

- The project follows Spring Boot conventions for package structure and naming
- MyBatis mappers should be placed in appropriate packages (typically `mapper` or `repository`)
- Database configuration will need to be added to `application.properties` for data source setup
- The main application class is annotated with `@SpringBootApplication` which enables auto-configuration