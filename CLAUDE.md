# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4.0 application called **StockLab_Service** using Java 17. The project uses Maven for build management, MyBatis for database ORM, and MySQL as the database.

## Key Technologies
- **Spring Boot 3.4.0** - Main application framework with Spring Web
- **MyBatis 3.0.5** - SQL mapping framework with Spring Boot integration
- **MySQL 8.0.33** - Database with mysql-connector-j driver
- **Knife4j 4.5.0** - API documentation and testing (Swagger UI enhancement)
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
│   │   ├── StockLabServiceApplication.java    # Main Spring Boot application class (@MapperScan enabled)
│   │   ├── config/
│   │   │   └── SwaggerConfig.java             # Knife4j/Swagger configuration
│   │   ├── controller/
│   │   │   ├── HealthController.java          # Health check endpoints
│   │   │   ├── UserController.java            # User management API
│   │   │   └── PlatformServiceController.java # Platform service management API
│   │   ├── entity/
│   │   │   ├── User.java                      # User entity (users table)
│   │   │   ├── PlatformService.java           # Platform service entity (platform_services table)
│   │   │   └── UserOperationLog.java          # User operation log entity (user_operation_logs table)
│   │   └── mapper/
│   │       ├── UserMapper.java                # User MyBatis mapper interface
│   │       ├── PlatformServiceMapper.java     # Platform service MyBatis mapper interface
│   │       └── UserOperationLogMapper.java    # User operation log MyBatis mapper interface
│   └── resources/
│       ├── application.properties             # Configuration (database, MyBatis, Knife4j)
│       └── mapper/
│           ├── UserMapper.xml                 # User SQL mappings
│           ├── PlatformServiceMapper.xml      # Platform service SQL mappings
│           └── UserOperationLogMapper.xml     # User operation log SQL mappings
└── test/
    └── java/com/example/stocklab_service/
        └── StockLabServiceApplicationTests.java # Main test class

doc/
├── init.sql                                   # Database initialization script
├── 20250807040000-add-url-type-and-other-info-to-platform-services.js # Migration file
├── 20250807050000-add-role-field-to-users.js # Migration file
└── 20250911071503-add-agent-type-support.js  # Migration file
```

## Architecture Notes

- **Package Structure**: Uses standard Maven layout with `com.example.stocklab_service` as the base package
- **Database Integration**: MyBatis is fully configured with MySQL database connection (`stocklab` database)
- **API Documentation**: Knife4j provides enhanced Swagger UI at `http://localhost:8090/doc.html`
- **Database Schema**: Three main tables - `users`, `platform_services`, `user_operation_logs`
- **Configuration**: Spring Boot auto-configuration enabled; settings in `application.properties`
- **Web Server**: Runs on port 8090 (configurable)

## Database Configuration

- **Database**: MySQL 8.0+ required
- **Connection**: localhost:3306/stocklab
- **Credentials**: username=root2, password=123456
- **Tables**: Run `doc/init.sql` to initialize database schema
- **Migrations**: Apply migration files in doc/ directory for schema updates

## API Documentation & Testing

- **Knife4j UI**: http://localhost:8090/doc.html
- **OpenAPI Spec**: http://localhost:8090/v3/api-docs
- **Health Check**: http://localhost:8090/api/health
- **Features**: Full CRUD operations for Users and Platform Services

## Development Guidelines

- **MyBatis Usage**: Entity classes map to database tables, Mapper interfaces define operations, XML files contain SQL
- **Controller Development**: Use `@Tag` and `@Operation` annotations for API documentation
- **Database Operations**: All CRUD operations are implemented via MyBatis mappers
- **API Standards**: RESTful endpoints with proper HTTP status codes
- **Error Handling**: Controllers return appropriate ResponseEntity objects