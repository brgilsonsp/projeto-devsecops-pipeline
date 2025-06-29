# projeto-devsecops-pipeline

This project is a Spring Boot application designed to implement a complete DevSecOps pipeline. It includes a Docker container setup and features a specific endpoint that returns a JSON response.

## Project Structure

- **.github/workflows**: Contains CI/CD pipeline configurations.
  - `ci-feature.yml`: CI pipeline for feature branches.
  - `ci-cd-develop.yml`: CI/CD pipeline for the develop branch.
  - `ci-cd-main.yml`: CD pipeline for the main branch.
  
- **devcontainer**: Configuration for the development container environment.
  - `devcontainer.json`: Specifies Docker image and development settings.

- **Dockerfile**: Instructions to build the Docker image for the Spring Boot application.

- **./app/src/main/java/com/example/demo**: Contains the main application code.
  - `DemoApplication.java`: Main application class.
  - **controller**: Contains controllers for handling requests.
    - `ResultController.java`: Exposes the "/resultado" endpoint.

- **./app/src/main/resources**: Contains application resources.
  - `application.properties`: Configuration properties for the Spring Boot application.
  - **static**: Directory for static resources (currently empty).

- **test/java/com/example/demo**: Contains unit tests.
  - `DemoApplication.java`: Tests for the application context.

- **security**: Contains security-related configurations.
  - `sonar-project.properties`: Configuration for SonarQube.
  - `dependency-check-suppressions.xml`: Suppressions for OWASP Dependency Check.
  - `gitleaks.toml`: Configuration for GitLeaks.

- **docs**: Documentation for the project.
  - `arquitetura-pipeline.md`: Architecture documentation for the pipeline.
  - `README.md`: Technical documentation for the project.

- **pom.xml**: Maven configuration file for project dependencies and build settings.

## Setup Instructions

1. Clone the repository.
2. Build the Docker image using the provided Dockerfile.
3. Run the application in a Docker container.
4. Access the endpoint at `/result` to receive a JSON response.

## Security Practices

This project implements various security tools and practices throughout the CI/CD pipeline to ensure the integrity and security of the application. Regular security scans and checks are integrated into the workflow to identify and mitigate vulnerabilities.

## Usage

To use the application, ensure that the Docker container is running and access the `/result` endpoint to retrieve the JSON response.