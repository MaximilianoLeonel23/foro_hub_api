# Foro Hub API

Foro Hub is an API designed to manage users and issues in a forum. This document provides an overview of the API's functionalities and how to use them.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA (Java Persistence API)
- MySQL
- Flyway
- Auth0

## Endpoints

### Authentication

#### Login

Endpoint = `POST /login`

Description: authenticates a user and generates a JWT token.

Request:

```json
{
"email": "user@example.com",
"pwd": "password"
}
```

Response: 

```json
{
"token": "jwt_token"
}
```

#### Issues

- Get all issues

Endpoint = `GET /issues` (Requires bearer token from login)

Description: retrieves a list of all issues.

Response: 

```json
[
{
"id": 1,
"title": "Issue Title",
"message": "Issue description",
"created_at": "2023-07-11T10:00:00",
"status": "Unanswered",
"author": "Issue Author",
"course": "Related Course"
}
]
```

- Create a new issue

Endpoint = `POST /issues` (Requires bearer token from login)

Description: creates a new issue.

Request:

```json
{
"title": "Issue Title",
"message": "Issue description",
"status": "Unanswered",
"author": "Issue Author",
"course": "Related Course"
}
```

Response: 

```json
{
"id": 1,
"title": "Issue Title",
"message": "Issue description",
"created_at": "2023-07-11T10:00:00",
"status": "Unanswered",
"author": "Issue Author",
"course": "Related Course"
}
```

- Update an issue

Endpoint = `PUT /issues/{id}` (Requires bearer token from login)

Description: updates an issue.

Request:

```json
{
"title": "New Issue Title",
"message": "New issue description",
}
```

Response:

```json
{
"id": 1,
"title": "New Issue Title",
"message": "New issue description",
"created_at": "2023-07-11T10:00:00",
"status": "Resolved",
"author": "New Issue Author",
"course": "New Related Course"
}
```


- Delete an issue

Endpoint = `DELETE /issues/{id}` (Requires bearer token from login)

Description: delete an issue.

Response: 204 No Content

- Get an issue details

Endpoint = `GET /issues/{id}` (Requires bearer token from login)

Description: retrieves the details of a specific issue.

Response:

```json
{
"id": 1,
"title": "Issue Title",
"message": "Issue description",
"created_at": "2023-07-11T10:00:00",
"status": "Unanswered",
"author": "Issue Author",
"course": "Related Course"
}
```





