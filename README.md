# Social Media API

This project aims to develop a RESTful API for a social media platform that allows users to register, log in, create posts, communicate, follow other users, and receive their activity feed.

## Requirements

1. ### Authentication and Authorization:
- Users can register by providing a username, email, and password.
- Users can log in with correct credentials.
- User data should be protected, including password hashing and JWT usage.
2. ### Post Management:
- Users can create posts with text, title, and images.
- Users can view posts of other users.
- Users can update and delete their own posts.
3. ### User Interaction:
- Users can send friend requests to others, becoming subscribers until accepted or declined.
- Accepted friend requests result in both users becoming friends.
- Users can unfriend others, with the recipient becoming a subscriber.
- Friends can exchange messages.
4. ### Subscriptions and Activity Feed:
- User activity feeds show recent posts from followed users.
- Activity feed supports pagination and time-based sorting.
5. ### Error Handling:
- Clear error messages should be returned for invalid requests or server issues.
- Input data should be validated, returning informative messages for incorrect formats.
6. ### API Documentation:
- API should be well-documented using tools like Swagger or OpenAPI.
- Documentation should include endpoint descriptions, request/response formats, and authentication requirements.
  
## Technologies and Tools

- Programming Language: Java 1.8 and higher (was tested on Java 17)
- Framework: Spring, Spring Boot
- Database: PostgreSQL
- Authentication and Authorization: Spring Security
- API Documentation: Swagger or OpenAPI

## Expected Outcomes

- A well-developed RESTful API that fulfills the specified requirements.
- Well-structured and documented codebase.
- Comprehensive tests covering major API functionalities.
- API documentation describing available endpoints and their usage.

## How to Run

1. Install Java and Maven.
2. Clone the repository.
3. Configure your database settings in application.properties.
4. Build and run the project using Maven:

```   
mvn spring-boot:run
```

5. Access the API documentation (Swagger UI) at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html).

## Evaluation Criteria

- Adherence to requirements.
- Code scalability and adherence to development principles.
- Code readability.
