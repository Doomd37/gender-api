# Gender Classification API

## Description
This API classifies a given name by gender using the Genderize API and returns a processed response.

---

## Endpoint

GET /api/classify?name={name}

### Example
/api/classify?name=john

---

## Success Response (200)

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

---

## Error Responses

- 400 Bad Request → Missing or empty name
- 422 Unprocessable Entity → No prediction available
- 500 Internal Server Error → Server failure

All errors follow:

{
"status": "error",
"message": "error message"
}

---

## Tech Stack

- Java
- Spring Boot
- WebClient

---

## How to Run

1. Clone the repository
2. Open in IntelliJ
3. Run the application
4. Test:

http://localhost:8080/api/classify?name=john