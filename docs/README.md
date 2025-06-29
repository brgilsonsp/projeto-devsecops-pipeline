# projeto-devsecops-pipeline/docs/README.md

# DevSecOps Pipeline Project

## Overview
This project implements a Spring Boot application with a DevSecOps pipeline designed to ensure security at every stage of the CI/CD process. The application exposes a single endpoint, `/resultado`, which returns a JSON response.

## Architecture
The architecture of the pipeline includes multiple workflows defined in the `.github/workflows` directory, which handle Continuous Integration (CI) and Continuous Deployment (CD) for feature branches and main branches. The project also utilizes Docker for containerization and includes security tools to scan for vulnerabilities.

## Setup Instructions
1. **Clone the repository:**
   ```
   git clone https://github.com/brgilsonsp/projeto-devsecops-pipeline.git
   cd projeto-devsecops-pipeline
   ```

2. **Build the Docker image:**
   ```
   docker build -t devsecops-app .
   ```

3. **Run the Docker container:**
   ```
   docker run -p 8080:8080 devsecops-app
   ```

4. **Access the application:**
   Open your browser and navigate to `http://localhost:8080/result` to see the JSON response.

## Security Practices
The project incorporates various security measures, including:
- Lint checks and unit tests in the CI pipeline.
- Integration tests in the CD pipeline.
- Security scans using tools like OWASP Dependency Check and GitLeaks.

## Conclusion
This project serves as a comprehensive example of implementing a DevSecOps pipeline with a Spring Boot application, ensuring security and efficiency throughout the development lifecycle.