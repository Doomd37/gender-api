## Gender Classification API

Description

This project is a Spring Boot REST API that integrates with the Genderize API to classify a given name by gender. It processes the raw API response and returns a structured output with additional computed fields such as confidence level and processing timestamp.

The system also includes proper error handling, input validation, and standardized API responses.

Endpoint
Classify Name
GET /api/classify?name={name}
Example Request
GET /api/classify?name=john
Success Response (200 OK)
{
"status": "success",
"data": {
"name": "john",
"gender": "male",
"probability": 0.99,
"sample_size": 1234,
"is_confident": true,
"processed_at": "2026-04-01T12:00:00Z"
}
}
Business Logic
Data Processing

The raw response from the Genderize API is transformed as follows:

count → renamed to sample_size
is_confident is computed using:
probability ≥ 0.7
sample_size ≥ 100
processed_at is generated dynamically using UTC time on every request

Error Handling

The application uses a global exception handler (@RestControllerAdvice) to ensure consistent error responses across all endpoints.

Error Response Format
{
"status": "error",
"message": "error message"
}

Error Cases
400 Bad Request → Missing or empty name parameter
422 Unprocessable Entity → Invalid input or no prediction available from external API
500 Internal Server Error → Unexpected server side failure
502 Bad Gateway → External API (Genderize) failure or upstream service issue


# Architecture & Implementation

Key Components

# Controller Layer
Handles incoming HTTP requests
Exposes /api/classify endpoint

# Service Layer
Handles business logic
Calls external Genderize API using WebClient
Processes and transforms response data

# DTOs
GenderizeResponse → maps external API response
ClassifyResponse → formatted response sent to client
ApiResponse<T> → standard wrapper for all responses

# Exception Handling
Global exception handler using @RestControllerAdvice
Custom exceptions for validation and external API failures
External API

This project integrates with:

Genderize API → https://genderize.io
CORS Configuration

CORS is enabled to allow cross-origin requests:

Access-Control-Allow-Origin: *

# Tech Stack
Java 17+
Spring Boot
Spring WebFlux (WebClient)
Maven

# How to Run
1. Clone the repository
   git clone https://github.com/Doomd37/gender-api.git
2. Build the project
   mvn clean install
3. Run the application
   mvn spring-boot:run
4. Test endpoint
   http://localhost:8080/api/classify?name=john
   Notes
   Ensure the server is running before testing
   All responses follow a consistent JSON structure
   The API must be deployed for final submission
   Tested using Postman and browser fetch requests


# Author
Name: Eze Emmanuel
GitHub: Doomd37
Role: Backend Developer (Spring Boot)
Project: Gender Classification API