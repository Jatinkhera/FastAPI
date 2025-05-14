# API Automation Framework (Java + RestAssured)

## Objective
Validate major functionalities of the FastAPI implementation using automated Java-based API testing.

## Tech Stack
- Java 17
- RestAssured
- TestNG
- ExtentReports
- GitHub Actions
- GitHub Actions is used to run tests automatically on each push.

## Strategy
- Covers all CRUD operations (Create, Read, Update, Delete)
- Validates:
  - HTTP status codes
  - Response bodies
  - Headers
  - Error handling (negative tests)
- Supports request chaining (output of one request used in another)

## Maintainability
- Modular test structure 
- Environment configuration via `config.properties`

## Reporting
- Extent Reports generated after test runs
- Shows Pass/Fail/Skip clearly

## Challenges
- Handling dynamic data from FastAPI.
  
## Environment Configuration
Challenge: Support for dev, QA, prod.
Solution: Load base URL from `config.properties` using `ConfigManager.java`

## Report Clarity
Challenge: Making reports useful.
Solution: Used ExtentReports for structured and readable HTML output.

