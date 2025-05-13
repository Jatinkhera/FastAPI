# API Automation Framework (Java + RestAssured)

## Objective
Validate major functionalities of the FastAPI implementation using automated Java-based API testing.

## Strategy

- Each test covers one full CRUD operation to ensure atomicity.
- Positive and negative scenarios
- Request chaining is implemented using shared variables (user ID).
- Tests include both success and failure paths (e.g., 200, 400, 404).
- Config-driven to easily switch between environments
- TestNG + RestAssured + Extent report
- GitHub Actions CI pipeline

## CI/CD
- GitHub Actions is used to run tests automatically on each push.
- Test report is stored as an artifact for each run.

## Challenges
- Handling dynamic data from FastAPI.
- Mitigated using JSON schema validations and custom data generators.

```bash
mvn clean test
