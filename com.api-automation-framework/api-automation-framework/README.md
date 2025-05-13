# Java API Automation Framework

## Objective
To validate FastAPI endpoints using a scalable and reusable API test framework.

## Features
- Full CRUD testing with request chaining
- Positive & negative scenarios
- Configurable environments
- Allure reports
- GitHub Actions CI/CD

## Setup

1. Update `config.properties` with your FastAPI base URL.
2. Run `mvn clean test` to execute tests.
3. Run `mvn allure:report` to generate reports.

## CI/CD

CI pipeline is configured using GitHub Actions (`.github/workflows/ci.yml`) to run tests on every push.

## Challenges

- Mapping the dynamic nature of request chaining
- Ensuring consistent test data cleanup (handled via delete)
